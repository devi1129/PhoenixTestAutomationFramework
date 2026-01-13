package com.api.tests;

import static com.api.constant.Roles.FD;
import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.requestSpecwithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static com.api.utils.SpecUtil.responseSpec_TEXT;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;

import java.io.File;

import org.testng.annotations.Test;



public class MasterAPITest {

@Test(description ="Verify if master API is giving correct response",groups= {"api","regression","smoke"} )
public void masterAPITest()
{

   given()
  .spec(requestSpecwithAuth(FD))
   .when()
   .post("master")
   .then()
   .spec(responseSpec_OK())
   .body("message",equalTo("Success"))
   .body("data",notNullValue())
   // $ - root json to check if the outer json has key
   .body("$",hasKey("message"))
   // $ - if data has key
   .body("data",hasKey("mst_oem"))
   .body("data.mst_oem.size()",greaterThan(0))
   .body("data.mst_oem.id",everyItem(notNullValue()))
   .body(matchesJsonSchemaInClasspath("response-schema"+File.separator+"MasterAPIResponseSchema.json"));
}
@Test(description ="Verify if master API is giving correct status code for invalid token",groups= {"api","regression","smoke"} )
public void MasterAPI_InvalidTest()
{
given()
  .spec(requestSpec())
   .when()
   .post("master")
   .then()
 .spec(responseSpec_TEXT(401));
   
}

}
