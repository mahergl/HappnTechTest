package com.happn.techTest;

import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.happn.techTest.model.CoordinatesDTO;
import com.happn.techTest.utils.FileReaderUtil;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilsTest {
	
		@Autowired
		FileReaderUtil fileReader;
		
	@Test
	public void FileNotEmptytest() throws IOException {
		 List<CoordinatesDTO> fileList = new ArrayList<CoordinatesDTO>();
		 fileList=fileReader.readFile();
		 
		 assertNotEquals(Collections.EMPTY_LIST, fileList);
		
		
	}

}
