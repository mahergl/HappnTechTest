package com.happn.techTest.api.rest.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.happn.techTest.api.rest.response.StringResponse;
import com.happn.techTest.model.CoordinatesDTO;
import com.happn.techTest.service.PoiCalculator;
import com.happn.techTest.utils.FileReaderUtil;

@RestController
public class CalculatePoiNumberController {
	private PoiCalculator poiCalculator;
	private FileReaderUtil fileReader;
	
	
	@Autowired
	public CalculatePoiNumberController(PoiCalculator poiCalculator, FileReaderUtil fileReader) {
		super();
		this.poiCalculator = poiCalculator;
		this.fileReader = fileReader;
	}



	
    @ResponseBody
    @GetMapping("/techtest/calculatepoi")
    public ResponseEntity<StringResponse> getPoiNumber(@RequestParam (value="min_lat", required=true)double minLat ,@RequestParam (value="min_lon", required=true) double minLon) throws IOException {
    	
			List<CoordinatesDTO> coorList = fileReader.readFile();
			return ResponseEntity.ok(poiCalculator.calculatePoisNumber(minLat, minLon, coorList));
			
		
    }
}
