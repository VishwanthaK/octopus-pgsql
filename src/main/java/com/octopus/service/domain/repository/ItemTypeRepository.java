package com.octopus.service.domain.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.octopus.service.domain.model.Item;
import com.octopus.service.domain.model.ItemType;

@Repository
public interface ItemTypeRepository extends JpaRepository<ItemType, Long> {
	
	@Query(" SELECT itemTypeObj from ItemType itemTypeObj "
		 + " WHERE itemTypeObj.recordStatus = ?1 ")
	List<ItemType> getItemTypesByStatus(Integer recordStatus);

}
