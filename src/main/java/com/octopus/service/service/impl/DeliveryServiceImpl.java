package com.octopus.service.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.octopus.service.domain.ApiResponse;
import com.octopus.service.domain.model.AppSettings;
import com.octopus.service.domain.model.OrderEntity;
import com.octopus.service.domain.model.UserSettings;
import com.octopus.service.domain.repository.AppSettingsRepository;
import com.octopus.service.domain.repository.OrderRepository;
import com.octopus.service.domain.repository.UserSettingsRepository;
import com.octopus.service.helper.OrderHelper;
import com.octopus.service.service.DeliveryService;
import com.octopus.service.service.UserService;
import com.octopus.service.util.AppConstants;
import com.octopus.service.util.AppUtil;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderHelper orderHelper;
    @Autowired
    private UserSettingsRepository userSettingsRepository;
    @Autowired
    private AppSettingsRepository appSettingsRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public ApiResponse getDeliveryList(
            final String token,
            final String filterBy,
            final String search,
            final Pageable pageable) {
        final Long userId = userService.getUserIdByToken(token);
        final UserSettings userSettings = userSettingsRepository.getActiveUserSettings(userId);
        final JsonNode userSettingsJson = AppUtil.jsonStringToJsonNode(userSettings.getSettings());
        final Boolean isCustomLocalityEnabled = Boolean.valueOf(AppUtil.getJsonValueByKey(userSettingsJson,
                AppConstants.USER_SETTING_KEYS.ENABLE_CUSTOM_LOCALITY));

        final String localityList  = getLocalities(userSettingsJson, isCustomLocalityEnabled);
        final Set<String> localitySet = Arrays
                .stream(localityList.split(","))
                .collect(Collectors.toSet());
        final List<OrderEntity>  orderList = orderRepository
                .getOrderListByLocalityList(localitySet, filterBy, null, pageable);

        return new ApiResponse(HttpStatus.OK.value(), orderHelper.getDeliveryList(orderList));
    }

    private String getLocalities(final JsonNode userSettingsJson, final Boolean isCustomLocalityEnabled) {
        String localityList;
        if (isCustomLocalityEnabled) {
            localityList = AppUtil
                    .getJsonValueByKey(userSettingsJson, AppConstants.USER_SETTING_KEYS.CUSTOM_LOCALITIES);
        } else {
            localityList = getDefaultLocalities(userSettingsJson);
        }

        return localityList;
    }

    private String getDefaultLocalities(final JsonNode userSettingsJson) {
        final String localityGrpIds = AppUtil.getJsonValueByKey(userSettingsJson,
                AppConstants.USER_SETTING_KEYS.PREFERRED_LOCALITY_GROUP);
        final String[] localityGrpIdArray = localityGrpIds.split(",");
        final AppSettings appSettings = appSettingsRepository
                .findOneByRecordStatus(AppConstants.ACTIVE_RECORD_STATUS);
        final JsonNode appSettingJson = AppUtil.jsonStringToJsonNode(appSettings.getSettings());
        final JsonNode localityGroup = appSettingJson.get(AppConstants.APP_SETTING_KEYS.LOCALITY_GROUP);
        final StringBuilder localities = new StringBuilder();
        Arrays.stream(localityGrpIdArray)
              .forEach(localityGrpId -> {
                  final String localitiesList =  AppUtil.getJsonValueByKey(localityGroup, localityGrpId);
                  localities.append(localitiesList);
              });

        return localities.toString();
    }
}
