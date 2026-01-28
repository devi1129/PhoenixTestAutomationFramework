package com.api.tests.datadriven;

import static com.api.constant.Roles.FD;
import static com.api.utils.DateTimeUtil.getTimewithDaysAgo;
import static com.api.utils.SpecUtil.requestSpecwithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.ServiceLocation;
import com.api.constant.Warranty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;

public class CreateJobAPIDatadrivenTest {
	
	
	
	
	@Test(description ="Verify if CreateJob API is able to create Inwarranty job",groups= {"api","regression","smoke","csv"},dataProviderClass = com.dataproviders.DataProviderUtils.class,dataProvider = "CreateJobAPIDataProvider" )
	public void createJobAPITest(CreateJobPayload payload)
	{
		
		
		// createjob payload
		
		
		
		
		
		
		given()
		.spec(requestSpecwithAuth(FD,payload))
		.when()
		.post("/job/create")
		.then()
		.spec(responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("response-schema"+File.separator+"CreateJobAPIResponseSchema.json"))
		.body("message", equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", equalTo(1))
		.body("data.job_number", startsWith("JOB_"));
		
	}

}
