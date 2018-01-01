package com.octopus.service.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.octopus.service.domain.model.Gst;
import com.octopus.service.domain.model.ItemType;

@Repository
public interface GstRepo extends JpaRepository<Gst, Long>{
	
	@Query(" SELECT gstSlabObj from Gst gstSlabObj "
		 + " WHERE gstSlabObj.recordStatus = ?1 ")
	List<Gst> getGstSlabsByStatus(Integer recordStatus);

}
