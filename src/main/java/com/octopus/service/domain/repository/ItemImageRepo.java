package com.octopus.service.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.octopus.service.domain.model.ItemImage;

@Repository
public interface ItemImageRepo extends JpaRepository<ItemImage, Long>{

}
