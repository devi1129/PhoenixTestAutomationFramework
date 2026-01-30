package com.api.tests.datadriven;

import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.File;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.dataproviders.api.Bean.UserBean;

public class LoginAPIJsonDataDrivenTest {
	
private	UserCredentials creds;
	
	@Test(description = "Verifying if login api is working for user iamfd" , groups = {"api","regression","smoke","csv"},
			dataProviderClass =com.dataproviders.DataProviderUtils.class 
			,dataProvider = "loginAPIJsonDataProvider")
	public void loginAPITest(UserCredentials userCredentials)
	{
		
		
		given()
		.spec(requestSpec(userCredentials))		
		.and()
		.when()
		.post("login")
		.then()
		.spec(responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("response-schema"+File.separator+"LoginResponseSchema.json"));
		
	}
	
	

}
