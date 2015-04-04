package com.mob.rs.server.model.feed;

public class Location {
	
	private Integer geoLocationId;

	private String address1;

	private String address2;

	private String city;

	private String county;

	private String state;

	private String stateCode;

	private String country;

	private String countryCode;

	private String zip;
	
	public Location() {
		super();
	}
	
	public Location(String address1, String address2, String city,
			String county, String state, String stateCode, String country,
			String countryCode, String zip) {
		super();
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.county = county;
		this.state = state;
		this.stateCode = stateCode;
		this.country = country;
		this.countryCode = countryCode;
		this.zip = zip;
	}



	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Integer getGeoLocationId() {
		return geoLocationId;
	}

	public void setGeoLocationId(Integer geoLocationId) {
		this.geoLocationId = geoLocationId;
	}

}
