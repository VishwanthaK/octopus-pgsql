package com.octopus.service.dto;

public class AddressDTO {


    private String alternateContactNumber;
    private String houseOrFlatNum;
    private String buildingOrHouseName;
    private String street;
    private String landmark;
    private String locality;
    private String city;
    private Integer pincode;

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
