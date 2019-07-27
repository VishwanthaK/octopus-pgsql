package com.octopus.service.dto;

import java.io.Serializable;

public class UserAddressDTO implements Serializable {

    private static final long serialVersionUID = 8645057929386007650L;

    private Long userId;
    private Long userAddressId;

    private String userName;
    private String userEmail;
    private String userPhoneNumber;

    private String alternateContactNumber;
    private String houseOrFlatNum;
    private String buildingOrHouseName;
    private String street;
    private String landmark;
    private String locality;
    private String city;
    private Integer pincode;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(Long userAddressId) {
        this.userAddressId = userAddressId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getAlternateContactNumber() {
        return alternateContactNumber;
    }

    public void setAlternateContactNumber(String alternateContactNumber) {
        this.alternateContactNumber = alternateContactNumber;
    }

    public String getHouseOrFlatNum() {
        return houseOrFlatNum;
    }

    public void setHouseOrFlatNum(String houseOrFlatNum) {
        this.houseOrFlatNum = houseOrFlatNum;
    }

    public String getBuildingOrHouseName() {
        return buildingOrHouseName;
    }

    public void setBuildingOrHouseName(String buildingOrHouseName) {
        this.buildingOrHouseName = buildingOrHouseName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }
}
