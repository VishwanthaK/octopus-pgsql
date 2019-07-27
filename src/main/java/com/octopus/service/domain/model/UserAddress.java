package com.octopus.service.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.octopus.service.domain.BaseModel;
import com.octopus.service.dto.AddressDTO;

@Entity
@Table(name = "user_address")
@JsonFilter("USER_ADDRESS_FILTER")
public class UserAddress extends BaseModel {
	
	private static final long serialVersionUID = 8361868273494574948L;

	private User userObj;
	
	private String alternateContactNumber;
	private String houseOrFlatNum;
	private String buildingOrHouseName;
	private String street;
	private String landmark;
	private String locality;
	private String city;
	private Integer pincode;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	public User getUserObj() {
		return userObj;
	}
	public void setUserObj(User userObj) {
		this.userObj = userObj;
	}
	
	@Column(name = "alternate_contact_number")
	public String getAlternateContactNumber() {
		return alternateContactNumber;
	}
	public void setAlternateContactNumber(String alternateContactNumber) {
		this.alternateContactNumber = alternateContactNumber;
	}
	
	@Column(name = "house_or_flat_no")
	public String getHouseOrFlatNum() {
		return houseOrFlatNum;
	}
	public void setHouseOrFlatNum(String houseOrFlatNum) {
		this.houseOrFlatNum = houseOrFlatNum;
	}
	
	@Column(name = "building_or_house_name")
	public String getBuildingOrHouseName() {
		return buildingOrHouseName;
	}
	public void setBuildingOrHouseName(String buildingOrHouseName) {
		this.buildingOrHouseName = buildingOrHouseName;
	}
	
	@Column(name = "street")
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	@Column(name = "landmark")
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	
	@Column(name = "locality")
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	
	@Column(name = "city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name = "pincode")
	public Integer getPincode() {
		return pincode;
	}
	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public static AddressDTO convertToAddressDTO(final UserAddress userAddress) {
		final AddressDTO addressDTO = new AddressDTO();

		addressDTO.setAlternateContactNumber(userAddress.getAlternateContactNumber());
		addressDTO.setBuildingOrHouseName(userAddress.buildingOrHouseName);
		addressDTO.setHouseOrFlatNum(userAddress.houseOrFlatNum);
		addressDTO.setLandmark(userAddress.getLandmark());
		addressDTO.setStreet(userAddress.getStreet());
		addressDTO.setLocality(userAddress.getLocality());
		addressDTO.setCity(userAddress.getCity());
		addressDTO.setPincode(userAddress.getPincode());

		return addressDTO;
	}
}
