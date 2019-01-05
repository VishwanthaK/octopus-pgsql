package com.octopus.service.domain.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.octopus.service.domain.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	
	@Query(" SELECT itemObj from Item itemObj "
		 + " JOIN itemObj.itemType itemTypeEntity "
		 + " WHERE itemObj.recordStatus = 1 AND itemTypeEntity.id = ?1 ")
	List<Item> getItembyType(Long itemTypeId, Pageable pageable);

}
