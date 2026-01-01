package com.api.tests;

import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import  static com.api.constant.Roles.*;
import static com.api.utils.AuthTokenProvider.*;

import static com.api.utils.ConfigManager.*;

import  static io.restassured.RestAssured.*;
public class CountAPITest {

	
	@Test
	public void verifyCountAPIResponse()
	{
		
		given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.header("Authorization",getToken(FD))
		.when()
		.get("/dashboard/count")
		.then()
		.statusCode(200)
		.time(lessThan(1200L))
		.body("message",equalTo("Success"))
		.body("data",notNullValue())
		.body("data.size()", equalTo(3))
        .body("data.count",everyItem(greaterThanOrEqualTo(0)))
        .body("data.label",everyItem(not(blankOrNullString())))
        .body("data.key",containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema"+File.separator+"CountAPIResponseSchema"))
        .log().all();
		
	}
	
	
	@Test
	public void verifyCountAPI_MissingAuthToken()
	{
		
		given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.when()
		.get("/dashboard/count")
		.then()
		.statusCode(401)
        .log().all();
		
	}
	
	
	
	
}
