package com.api.tests;

import  static io.restassured.RestAssured.*;






import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;

import static com.api.utils.SpecUtil.*;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	@Test
	public void loginAPITest()
	{
		
		UserCredentials creds=new UserCredentials("iamfd", "password");
		given()
		.spec(requestSpec(creds))		
		.and()
		.when()
		.post("login")
		.then()
		.spec(responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema"+File.separator+"LoginResponseSchema.json"));
		
	}

}
