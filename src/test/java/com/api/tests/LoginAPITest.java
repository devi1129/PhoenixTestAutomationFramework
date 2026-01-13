package com.api.tests;

import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.File;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;

public class LoginAPITest {
	
private	UserCredentials creds;
	
	@Test(description = "Verifying if login api is working for user iamfd" , groups = {"api","regression","smoke"})
	public void loginAPITest()
	{
		
		
		given()
		.spec(requestSpec(creds))		
		.and()
		.when()
		.post("login")
		.then()
		.spec(responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("response-schema"+File.separator+"LoginResponseSchema.json"));
		
	}
	
	@BeforeMethod(description = "Create the payload for the Login API")
	public void setup()
	{
		creds=new UserCredentials("iamfd", "password");
	}

}
