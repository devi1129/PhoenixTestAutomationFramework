package com.api.tests;

import static com.api.constant.Roles.FD;
import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.requestSpecwithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static com.api.utils.SpecUtil.responseSpec_TEXT;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;
public class CountAPITest {

	
	@Test(description ="Verify if count API is giving correct response",groups= {"api","regression","smoke"} )
	public void verifyCountAPIResponse()
	{
		
		given()
		.spec(requestSpecwithAuth(FD))
		.when()
		.get("/dashboard/count")
		.then()
		.spec(responseSpec_OK())
		.body("message",equalTo("Success"))
		.body("data",notNullValue())
		.body("data.size()", equalTo(3))
        .body("data.count",everyItem(greaterThanOrEqualTo(0)))
        .body("data.label",everyItem(not(blankOrNullString())))
        .body("data.key",containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema"+File.separator+"CountAPIResponseSchema"))
        .log().all();
		
	}
	
	
	@Test(description ="Verify if count API is giving correct status code for Invalid Token",groups= {"api","regression","smoke"} )
	public void verifyCountAPI_MissingAuthToken()
	{
		
		given()
		.spec(requestSpec())
		.when()
		.get("/dashboard/count")
		.then()
		.spec(responseSpec_TEXT(401));


		
		
	}
	
	
	
	
}
