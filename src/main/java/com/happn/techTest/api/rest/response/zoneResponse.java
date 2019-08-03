package com.happn.techTest.api.rest.response;

public class zoneResponse {
	
	private double minLat;
	private double maxLat;
	private double minLon;
	private double maxLon;
	public zoneResponse(double minLat, double maxLat, double minLon, double maxLon) {
		super();
		this.minLat = minLat;
		this.maxLat = maxLat;
		this.minLon = minLon;
		this.maxLon = maxLon;
	}
	public double getMinLat() {
		return minLat;
	}
	public void setMinLat(double minLat) {
		this.minLat = minLat;
	}
	public double getMaxLat() {
		return maxLat;
	}
	public void setMaxLat(double maxLat) {
		this.maxLat = maxLat;
	}
	public double getMinLon() {
		return minLon;
	}
	public void setMinLon(double minLon) {
		this.minLon = minLon;
	}
	public double getMaxLon() {
		return maxLon;
	}
	public void setMaxLon(double maxLon) {
		this.maxLon = maxLon;
	}
	
	
	

}
