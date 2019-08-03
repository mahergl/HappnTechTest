package com.happn.techTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.happn.techTest.api.rest.response.StringResponse;
import com.happn.techTest.api.rest.response.zoneResponse;
import com.happn.techTest.model.CoordinatesDTO;
import com.happn.techTest.service.impl.PoiCalculatorImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTests extends RestTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Autowired
	PoiCalculatorImpl poiCalculatorImpl;

	@Test
	@Order(1)
	public void successCalculatingPoiTest() {
		assertEquals("2", poiCalculatorImpl.calculatePoisNumber(6.5, -7, createCoordinatesList()).getValue());
	}

	@Test
	@Order(2)
	public void nullCalculatingPoiTest() {

		assertEquals("0", poiCalculatorImpl.calculatePoisNumber(50, 50, createCoordinatesList()).getValue());
	}

	@Test
	@Order(3)
	public void successSearchingZonesTest() {
		zoneResponse testResponse = new zoneResponse(-2.0, -2.5, 38.0, 38.5);
		assertThat(testResponse.equals(poiCalculatorImpl.searchDensity(1, createCoordinatesList()).get(0)));

	}

	@Test
	@Order(4)
	public void getPoinumberRestTest() throws Exception {
		String uri = "http://localhost:8181/techtest/calculatepoi";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)
				.param("min_lat", "6.5").param("min_lon", "-7")).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		StringResponse response = new StringResponse("2");
		assertThat(response.getValue().toString().equals(content));

	}

	@Test
	@Order(5)
	public void searchDensityRestTest() throws Exception {
		String uri = "http://localhost:8181/techtest/searchdensity";
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE).param("n", "1"))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		zoneResponse testResponse = new zoneResponse(-2.0, -2.5, 38.0, 38.5);
		assertThat(testResponse.toString().equals(content));

	}
	
	
	public List<CoordinatesDTO> createCoordinatesList(){
		List<CoordinatesDTO> coorList = new ArrayList<CoordinatesDTO>();
		coorList.add(new CoordinatesDTO("id1", -48.6, -37.7));
		coorList.add(new CoordinatesDTO("id2", -27.1, 8.4));
		coorList.add(new CoordinatesDTO("id3", 6.6, -6.9));
		coorList.add(new CoordinatesDTO("id4", -2.3, 38.3));
		coorList.add(new CoordinatesDTO("id5", 6.8, -6.9));
		coorList.add(new CoordinatesDTO("id6", -2.5, 38.3));
		coorList.add(new CoordinatesDTO("id7", 0.1, -0.1));
		coorList.add(new CoordinatesDTO("id8", -2.1, 38.1));	
		return coorList;
	}

}
