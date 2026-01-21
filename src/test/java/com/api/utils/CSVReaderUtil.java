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
	
	
	public static Iterator<UserBean> loadCSV(String path){
		
		
		
		 InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		 
		
		 InputStreamReader isr=new InputStreamReader(resourceAsStream);
		
		
		CSVReader csv=new CSVReader(isr);
		

		
		CsvToBean<UserBean> creds=new CsvToBeanBuilder(csv).withType(UserBean.class)
				                   .withIgnoreEmptyLine(true)
				                   .build();
		
		List<UserBean> list = creds.parse();
		
		System.out.println(list.toString());
		
		return list.iterator();
	
		
	}

}
