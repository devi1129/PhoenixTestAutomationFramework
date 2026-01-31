package com.dataproviders;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.UserCredentials;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.ExcelReaderUtil;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JsonReaderUtil;
import com.dataproviders.api.Bean.CreateJobBean;
import com.dataproviders.api.Bean.UserBean;

public class DataProviderUtils {
	
	
	@DataProvider(name="loginAPIDataProvider",parallel = true)
	public static Iterator<UserBean> loginAPIDataProvider()
	{
		
		return CSVReaderUtil.loadCSV("testData"+File.separator+"logincreds.csv",UserBean.class );
		
	}
	
	@DataProvider(name="CreateJobAPIDataProvider",parallel = true)
	public static Iterator<CreateJobPayload> CreateJobAPIDataProvider()
	{
		
		
		
	Iterator<CreateJobBean> bean=	 CSVReaderUtil.loadCSV("testData"+File.separator+"CreateJobData.csv",CreateJobBean.class);
	
    List<CreateJobPayload> payloadlist=new ArrayList();
    CreateJobPayload payload;
	while (bean.hasNext()) {
		CreateJobBean createJobBean = (CreateJobBean) bean.next();
		
	 payload=	CreateJobBeanMapper.mapper(createJobBean);
	
	payloadlist.add(payload);
		
	}
	
	return payloadlist.iterator();
		
	}
	
	
	
	
	@DataProvider(name="CreateJobAPIFakerDataProvider",parallel = true)
	public static Iterator<CreateJobPayload> CreateJobAPIFakerDataProvider()
	{
		
		String fakercount=System.getProperty("fakercount", "5");
		int count=Integer.parseInt(fakercount);

		
	return	FakerDataGenerator.generateFakeCreateJobData(count);
		
	}
	
	
	@DataProvider(name="loginAPIJsonDataProvider",parallel = true)
	public static Iterator<UserCredentials> loginAPIJsonDataProvider()
	{
		
		return JsonReaderUtil.loadJSON("testData"+File.separator+"loginAPITestData.json",UserCredentials[].class );
		
	}
	
	
	
	@DataProvider(name="CreateJobAPIJsonDataProvider",parallel = true)
	public static Iterator<CreateJobPayload> CreateJobAPIJsonDataProvider()
	{
		
		
		
		return JsonReaderUtil.loadJSON("testData"+File.separator+"CreateJobAPIData.json",CreateJobPayload[].class );
	}
   
	@DataProvider(name="loginAPIExcelDataProvider",parallel = true)
	public static Iterator<UserBean> loginAPIExcelDataProvider()
	{
		
		return ExcelReaderUtil.loadTestData("LoginTestData",UserBean.class);
		
	}

}
