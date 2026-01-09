package com.api.tests;

import static io.restassured.RestAssured.given;



import static org.hamcrest.Matchers.lessThan;

import java.io.File;

import org.testng.annotations.Test;

import  static com.api.utils.SpecUtil.*;

import static com.api.constant.Roles.*;

import static com.api.utils.AuthTokenProvider.*;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;

public class UserDetailsAPITest {

	@Test
	public void UserDetailsAPITest() {
		
		
		
		
		given()
		.spec(requestSpecwithAuth(FD))
		.when()
		.get("userdetails")
		.then()
		.spec(responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema"+File.separator+"UserDetailsResponseSchema.json"));
		
		

	}

}
