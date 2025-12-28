package com.api.tests;

import  static io.restassured.RestAssured.*;




import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

import com.api.POJO.UserCredentials;
import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	@Test
	public void loginAPITest()
	{
		
		UserCredentials creds=new UserCredentials("iamfd", "password");
		given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.contentType(ContentType.JSON)
		.and()
		.accept(ContentType.JSON)
		.and()
		.body(creds)
		.log().uri()
		.log().method()
		.log().headers()
		.log().body()
		.when()
		.post("login")
		.then()
		.statusCode(200)
		.time(lessThan(1200L))
		.body("message",equalTo("Success") )
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema"+File.separator+"LoginResponseSchema.json"));
		
	}

}
