package com.happn.techTest.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.happn.techTest.model.CoordinatesDTO;
@Component
public class FileReaderUtil {

	
	 @Value("${file.path}")
	  private String INPUT_FILE;
	
	public List<CoordinatesDTO> readFile() throws IOException {
		BufferedReader tsvFile =null;
		try {
			//lire le fichier tsv avec un bufferReader
			tsvFile=new BufferedReader(new FileReader(INPUT_FILE));
			//mapper le fichier en liste 
			List<CoordinatesDTO> dataRows = tsvFile.lines().skip(1).map(dataRow->{
					CoordinatesDTO coordinates = new CoordinatesDTO();
					String[] dataArray = dataRow.split("\t");
					coordinates.setId(dataArray[0]);
					coordinates.setLatitude(Double.valueOf(dataArray[1]));
					coordinates.setLongitude(Double.valueOf(dataArray[2]));
				return coordinates;	
			}).collect(Collectors.toList());
			
			return dataRows;
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Votre fichier n'existe pas ou la route est fausse");
			
		} finally {
			tsvFile.close();
		}
	}

}
