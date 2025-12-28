package com.api.tests;

import static io.restassured.RestAssured.given;


import static org.hamcrest.Matchers.lessThan;

import java.io.File;

import org.testng.annotations.Test;

import static com.api.constant.Roles.*;

import static com.api.utils.AuthTokenProvider.*;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {

	@Test
	public void UserDetailsAPITest() {
		
		
		Header authHeader=new Header("Authorization",getToken(FD));
		
		given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.header(authHeader)
		.and()
		.accept(ContentType.JSON)
		.when()
		.get("userdetails")
		.then()
		.log().all()
		.statusCode(200)
		.time(lessThan(1200L))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema"+File.separator+"UserDetailsResponseSchema.json"));
		
		

	}

}
