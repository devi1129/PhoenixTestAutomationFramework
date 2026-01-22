package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.dataproviders.api.Bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {
	
	
	private CSVReaderUtil()
	{
		
	}
	
	
	public static <T> Iterator<T> loadCSV(String path,Class<T> bean){
		
		
		
		 InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		 
		
		 InputStreamReader isr=new InputStreamReader(resourceAsStream);
		
		
		CSVReader csv=new CSVReader(isr);
		

		
		CsvToBean<T> creds=new CsvToBeanBuilder(csv).withType(bean)
				                   .withIgnoreEmptyLine(true)
				                   .build();
		
		List<T> list = creds.parse();
		
		System.out.println(list.toString());
		
		return list.iterator();
	
		
	}

}
