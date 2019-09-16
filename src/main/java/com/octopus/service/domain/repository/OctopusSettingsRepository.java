package com.octopus.service.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.octopus.service.domain.model.OctopusSettings;

@Repository
public interface OctopusSettingsRepository extends PagingAndSortingRepository<OctopusSettings, Long> {

    OctopusSettings findOneBySettingKeyAndRecordStatus(String settingKey, Integer recordStatus);
}
