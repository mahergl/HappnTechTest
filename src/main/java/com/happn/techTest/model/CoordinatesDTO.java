package com.happn.techTest.model;

public class CoordinatesDTO {

	private String id;
	private Double longitude;
	private Double latitude;
	
	
	
	
	public CoordinatesDTO() {
		super();
	}
	public CoordinatesDTO(String id, Double latitude, Double longitude) {
		super();
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	
}
