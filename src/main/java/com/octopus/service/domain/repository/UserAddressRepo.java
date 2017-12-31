package com.octopus.service.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.octopus.service.domain.model.UserAddress;

@Repository
public interface UserAddressRepo extends JpaRepository<UserAddress, Long> {
	
	@Query(" SELECT userAddress from UserAddress userAddress "
		 + " JOIN userAddress.userObj userEntity "
		 + " WHERE userAddress.recordStatus = 1 AND userEntity.id = ?1 ")
	List<UserAddress> getUserAddress(Long userId);

}
