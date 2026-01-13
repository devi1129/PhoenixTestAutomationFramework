package com.api.tests;

import static com.api.constant.Roles.FD;
import static com.api.utils.SpecUtil.requestSpecwithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.File;

import org.testng.annotations.Test;

public class UserDetailsAPITest {

	@Test(description ="Verify if the userdetais API response is shown correctly",groups= {"api","regression","smoke"} )
	public void UserDetailsAPITest() {
		
		
		
		
		given()
		.spec(requestSpecwithAuth(FD))
		.when()
		.get("userdetails")
		.then()
		.spec(responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("response-schema"+File.separator+"UserDetailsResponseSchema.json"));
		
		

	}

}
