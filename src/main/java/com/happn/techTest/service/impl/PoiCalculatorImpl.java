package com.happn.techTest.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.happn.techTest.api.rest.response.StringResponse;
import com.happn.techTest.api.rest.response.zoneResponse;
import com.happn.techTest.model.CoordinatesDTO;
import com.happn.techTest.service.PoiCalculator;

@Service
public class PoiCalculatorImpl implements PoiCalculator {
	// chercher le nombre de zone suivant des coordonnées bien définies
	@Override
	public StringResponse calculatePoisNumber(double lat, double lon, List<CoordinatesDTO> coorList) {
		List<Double> latZone = getZone(lat);
		List<Double> lonZone = getZone(lon);
		if (latZone.size() < 3 && lonZone.size() < 3) {
			Long result = coorList.stream()
					.filter(coor -> coor.getLatitude() > latZone.get(0) && coor.getLatitude() < latZone.get(1))
					.filter(coor -> coor.getLongitude() > getZone(lon).get(0)
							&& coor.getLongitude() < getZone(lon).get(1))
					.count();
			return new StringResponse(result.toString());
		}else if (latZone.size()== 3 && lonZone.size() < 3) {
			Long result = extracted(lon, coorList, latZone);
			return new StringResponse(result.toString());
		}else if (latZone.size()< 3 && lonZone.size() == 3) {
			Long result =  extracted(lon, coorList, lonZone);
			return new StringResponse(result.toString());
		}else {
			Long result = coorList.stream()
					.filter(coor -> (coor.getLatitude() > latZone.get(0) && coor.getLatitude() < latZone.get(1))
							||(coor.getLatitude() < latZone.get(0) && coor.getLatitude() > latZone.get(2)) )
					.filter(coor -> (coor.getLongitude() >lonZone.get(0) && coor.getLongitude() < lonZone.get(1))
							|| (coor.getLongitude() > lonZone.get(0) && coor.getLongitude() < lonZone.get(2)))
					.count();
			return new StringResponse(result.toString());
		}
	}
	// chercher les zones les plus dense
	@Override
	public List<zoneResponse> searchDensity(int number, List<CoordinatesDTO> coorList) {
		List<CoordinatesDTO> listByLat;
		List<CoordinatesDTO> finalList;
		List<CoordinatesDTO> tempList;
		List<zoneResponse> responseList = new ArrayList<zoneResponse>();
		List<List<CoordinatesDTO>> coordinatesList = new ArrayList<List<CoordinatesDTO>>();
		double maxLat = coorList.stream().max(Comparator.comparingDouble(CoordinatesDTO::getLatitude)).get()
				.getLatitude();
		double minLat = coorList.stream().min(Comparator.comparingDouble(CoordinatesDTO::getLatitude)).get()
				.getLatitude();
		for (double i = (int) minLat - 1; i < (int) (maxLat + 1); i = i + 0.5) {
			listByLat = new ArrayList<CoordinatesDTO>();
			for (CoordinatesDTO coorpos : coorList) {
				if (coorpos.getLatitude() >= i && coorpos.getLatitude() < i + 0.5) {
					listByLat.add(coorpos);
				}
			}
			if (!listByLat.isEmpty()) {
				double maxLon = listByLat.stream().max(Comparator.comparingDouble(CoordinatesDTO::getLongitude)).get()
						.getLongitude();
				double minLon = listByLat.stream().min(Comparator.comparingDouble(CoordinatesDTO::getLongitude)).get()
						.getLongitude();
				finalList = new ArrayList<CoordinatesDTO>();
				for (double j = (int) minLon - 1; j < (int) (maxLon + 1); j = j + 0.5) {
					for (CoordinatesDTO coorpos : listByLat) {
						if (coorpos.getLongitude() >= j && coorpos.getLongitude() < j + 0.5) {
							finalList.add(coorpos);
						}
					}
				}
				if (!finalList.isEmpty()) {
					coordinatesList.add(finalList);
				}
			}
		}
		for (int i = 0; i < number; i++) {
			int maxSize = 0;
			tempList = new ArrayList<CoordinatesDTO>();
			for (List<CoordinatesDTO> coordinates : coordinatesList) {
				if (coordinates.size() > maxSize) {
					maxSize = coordinates.size();
					tempList = coordinates;
				}
			}
			double maxLatitude = tempList.stream().max(Comparator.comparingDouble(CoordinatesDTO::getLatitude)).get()
					.getLatitude();
			double maxLongitude = tempList.stream().max(Comparator.comparingDouble(CoordinatesDTO::getLongitude)).get()
					.getLongitude();
			List<Double> temp1 = getZone(maxLatitude);
			List<Double> temp2 = getZone(maxLongitude);
			responseList.add(new zoneResponse(getZone(maxLatitude).get(0)>getZone(maxLatitude).get(1)?getZone(maxLatitude).get(1):getZone(maxLatitude).get(0)
											, getZone(maxLatitude).get(0)<getZone(maxLatitude).get(1)?getZone(maxLatitude).get(1):getZone(maxLatitude).get(0)
											, getZone(maxLongitude).get(0)>getZone(maxLongitude).get(1)?getZone(maxLongitude).get(1):getZone(maxLongitude).get(0)
											, getZone(maxLongitude).get(0)<getZone(maxLongitude).get(1)?getZone(maxLongitude).get(1):getZone(maxLongitude).get(0))
					);
			coordinatesList.remove(tempList);
		}

		return responseList;
	}

	// chercher la zone pour une coordonnée bien définie
	private List<Double> getZone(double coor) {
		List<Double> zone = new ArrayList<Double>();
		if (coor > 0) {
			if (coor - Math.floor(coor) > 0.5) {
				zone.add(Math.floor(coor) + 0.5);
				zone.add(Math.floor(coor) + 1);
			} else if (coor - Math.floor(coor) < 0.5 && coor - Math.floor(coor)> 0  ) {
				zone.add(Math.floor(coor));
				zone.add(Math.floor(coor) + 0.5);
			} else {
				zone.add(coor);
				zone.add(coor + 0.5);
				zone.add(coor - 0.5);
			}
		} else if (coor < 0) {
			double posMaxlatitude = Math.abs(coor);
			if (posMaxlatitude - Math.floor(posMaxlatitude) < 0.5 && posMaxlatitude - Math.floor(posMaxlatitude)>0) {
				zone.add(Math.ceil(coor));
				zone.add(Math.ceil(coor) - 0.5);
			} else if (posMaxlatitude - Math.floor(posMaxlatitude) > 0.5) {
				zone.add(Math.ceil(coor) - 0.5);
				zone.add(Math.ceil(coor) - 1);
			} else {
				zone.add(coor);
				zone.add(coor + 0.5);
				zone.add(coor - 0.5);
			}
		} else {
			zone.add(0.0);
			zone.add(0.5);
		}
		return zone;

	}
	
	private long extracted(double lon, List<CoordinatesDTO> coorList, List<Double> zone) {
		return coorList.stream()
				.filter(coor -> (coor.getLatitude() > zone.get(0) && coor.getLatitude() < zone.get(1))
						||(coor.getLatitude() < zone.get(0) && coor.getLatitude() > zone.get(2)) )
				.filter(coor -> coor.getLongitude() > getZone(lon).get(0)
						&& coor.getLongitude() < getZone(lon).get(1))
				.count();
	}

	
}
