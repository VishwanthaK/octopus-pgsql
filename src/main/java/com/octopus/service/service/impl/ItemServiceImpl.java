package com.octopus.service.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.octopus.service.domain.ApiResponse;
import com.octopus.service.domain.model.Item;
import com.octopus.service.domain.model.ItemImage;
import com.octopus.service.domain.model.OctopusSettings;
import com.octopus.service.domain.repository.ItemImageRepository;
import com.octopus.service.domain.repository.ItemRepository;
import com.octopus.service.domain.repository.OctopusSettingsRepository;
import com.octopus.service.exception.BadRequestexception;
import com.octopus.service.response.filter.ItemFilter;
import com.octopus.service.service.ItemService;
import com.octopus.service.util.AppConstants;
import com.octopus.service.util.AppMessages;
import com.octopus.service.util.AppUtil;

@Service
public class ItemServiceImpl implements ItemService {

	private static final Set<String> VALID_IMAGE_FILE_EXTENSIONS = Collections.unmodifiableSet(
			new HashSet<>(Arrays.asList("jpeg", "jpg", "png")));
	
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private OctopusSettingsRepository octopusSettingsRepository;
	@Autowired
	private ItemImageRepository itemImageRepository;

	
	@Override
	public void addItem(String token, Item item) {
		itemRepository.saveAndFlush(item);
	}

	@Override
	public void updateItem(String token, Item item) {
		itemRepository.getOne(item.getId()); //to validate given id is valid or not. throw 500 error code exception.
		itemRepository.saveAndFlush(item);
	}

	@Override
	public String getItem(String token, Long itemtypeId, Pageable pageable) 
			throws JsonProcessingException {
		List<Item> items = itemRepository.getItembyType(itemtypeId, pageable);
 		return ItemFilter.filterItemEntityList(items);
	}

	@Override
	public ApiResponse uploadImage(final String token, final Long itemId, final MultipartFile imageFile) {
		if (imageFile.isEmpty()) {
			throw new BadRequestexception("Invalid file!!");
		}

		final String originalFileName = imageFile.getOriginalFilename();
		final String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

		if (!VALID_IMAGE_FILE_EXTENSIONS.contains(extension)) {
			throw new BadRequestexception("Invalid image file!!");
		}

		final OctopusSettings octopusSettings = octopusSettingsRepository
				.findOneBySettingKeyAndRecordStatus(AppConstants.FILE_UPLOAD_PATH_KEY,
						AppConstants.ACTIVE_RECORD_STATUS);

		if (Objects.isNull(octopusSettings)) {
			throw new RuntimeException("Settings not found!!!");
		}

		final String uploadFolder = octopusSettings.getSettingValue();
		final String fileName = "image_" + itemId + "." + extension;

		AppUtil.uploadFile(uploadFolder, fileName, imageFile);

		final ItemImage itemImage = new ItemImage();
		final Item item = new Item();
		item.setId(itemId);
		itemImage.setItem(item);
		itemImage.setImagePath(uploadFolder + "/" +fileName);
		itemImageRepository.save(itemImage);

		return AppUtil.frameSuccessResponse(HttpStatus.OK.value(), AppMessages.SUCCESSFUL_UPLOAD);
	}
}
