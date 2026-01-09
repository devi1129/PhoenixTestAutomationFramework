package com.api.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.api.utils.ConfigManager.*;

import static com.api.utils.AuthTokenProvider.*;

import org.hamcrest.Matchers;

import com.api.constant.Roles;
public class SpecUtil {
	
	
	// get or delete requests
	public  static RequestSpecification requestSpec()
	{
		
		
		// to take care of the common request sections(methods)
		
		RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.log(LogDetail.METHOD)
		.log(LogDetail.URI)
		.log(LogDetail.HEADERS)
		.log(LogDetail.BODY)
		.build();
		
		return requestSpecification;
	}
	
	
	
	//post-put-patch
	public  static RequestSpecification requestSpec(Object body)
	{
		
		
		// to take care of the common request sections(methods)
		
		RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.setBody(body)
		.log(LogDetail.METHOD)
		.log(LogDetail.URI)
		.log(LogDetail.HEADERS)
		.log(LogDetail.BODY)
		.build();
		
		return requestSpecification;
	}
	
	public  static RequestSpecification requestSpecwithAuth(Roles role)
	{
		
		
		// to take care of the common request sections(methods)
		
		RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.addHeader("Authorization", getToken(role))
		.log(LogDetail.METHOD)
		.log(LogDetail.URI)
		.log(LogDetail.HEADERS)
		.log(LogDetail.BODY)
		.build();
		
		return requestSpecification;
	}
	
	public static ResponseSpecification responseSpec_OK()
	{
	ResponseSpecification responseSpecification = new ResponseSpecBuilder()
	.expectContentType(ContentType.JSON)
	.expectStatusCode(200)
	.expectResponseTime(Matchers.lessThan(2000L))
	.log(LogDetail.ALL)
	.build();
	
	return responseSpecification;
	}
	
	public static ResponseSpecification responseSpec()
	{
	ResponseSpecification responseSpecification = new ResponseSpecBuilder()
	.expectContentType(ContentType.JSON)
	.expectStatusCode(200)
	.expectResponseTime(Matchers.lessThan(2000L))
	.log(LogDetail.ALL)
	.build();
	
	return responseSpecification;
	}
	
	public static ResponseSpecification responseSpec(int status)
	{
	ResponseSpecification responseSpecification = new ResponseSpecBuilder()
	.expectContentType(ContentType.JSON)
	.expectStatusCode(status)
	.expectResponseTime(Matchers.lessThan(2000L))
	.log(LogDetail.ALL)
	.build();
	
	return responseSpecification;
	}
	
	public static ResponseSpecification responseSpec_TEXT(int status)
	{
	ResponseSpecification responseSpecification = new ResponseSpecBuilder()
	
	.expectStatusCode(status)
	.expectResponseTime(Matchers.lessThan(2000L))
	.log(LogDetail.ALL)
	.build();
	
	return responseSpecification;
	}
	
	
	

}
