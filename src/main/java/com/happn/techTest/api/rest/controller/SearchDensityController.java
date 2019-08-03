package com.happn.techTest.api.rest.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.happn.techTest.api.rest.response.zoneResponse;
import com.happn.techTest.model.CoordinatesDTO;
import com.happn.techTest.service.PoiCalculator;
import com.happn.techTest.utils.FileReaderUtil;

@RestController
public class SearchDensityController {
	
	private PoiCalculator poiCalculator;
	private FileReaderUtil fileReader;

	
	@Autowired
	public SearchDensityController(PoiCalculator poiCalculator, FileReaderUtil fileReader) {
		super();
		this.poiCalculator = poiCalculator;
		this.fileReader = fileReader;
	}



	@GetMapping("/techtest/searchdensity")
	@ResponseBody
	public ResponseEntity<List<zoneResponse>> getDensity(@RequestParam(value = "n", required = true) int zonesNumber)
			throws IOException {
			List<CoordinatesDTO> coorList = fileReader.readFile();
			return ResponseEntity.ok(poiCalculator.searchDensity(zonesNumber, coorList));
	}

}
