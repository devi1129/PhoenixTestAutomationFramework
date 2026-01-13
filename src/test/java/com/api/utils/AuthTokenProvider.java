package com.api.utils;

import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.File;

import com.api.constant.Roles;
import com.api.request.model.UserCredentials;

import static com.api.constant.Roles.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;


public class AuthTokenProvider {
	
	private AuthTokenProvider()
	{
		
	}

	public static String getToken(Roles role) {
	
		UserCredentials creds = null;
		if(role==FD)
		 creds=new UserCredentials("iamfd", "password");
		
		else if(role==SUP)
			 creds=new UserCredentials("iamsup", "password");
		else if(role==ENG)
			 creds=new UserCredentials("iameng", "password");
		else if(role==QC)
			 creds=new UserCredentials("iamqc", "password");
		
		
	String token=	given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.contentType(ContentType.JSON)
		.and()
		.accept(ContentType.JSON)
		.and()
		.body(creds)
		.when()
		.post("login")
		.then()
		.log().ifValidationFails()
		.statusCode(200)
		.extract()
		.body()
		.jsonPath()
		.getString("data.token");
	
	  return token;

	}

}
