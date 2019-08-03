package com.happn.techTest.service;

import java.util.List;

import com.happn.techTest.api.rest.response.StringResponse;
import com.happn.techTest.api.rest.response.zoneResponse;
import com.happn.techTest.model.CoordinatesDTO;

public interface PoiCalculator {

	StringResponse calculatePoisNumber(double minLat, double minLong , List<CoordinatesDTO> coorList);
	List<zoneResponse> searchDensity(int number, List<CoordinatesDTO> coorList);
	
}
