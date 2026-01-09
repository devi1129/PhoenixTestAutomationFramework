package com.api.tests;

import static org.hamcrest.Matchers.*;


import java.io.File;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.*;

import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.constant.Roles.*;
import static com.api.utils.AuthTokenProvider.*;

import static com.api.utils.ConfigManager.*;

import static io.restassured.RestAssured.*;

public class MasterAPITest {


@Test
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
   .body("data.mst_oem.id",everyItem(notNullValue()));
   //.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema"+File.separator+"MasterAPIResponseSchema.json"));
}

@Test
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
