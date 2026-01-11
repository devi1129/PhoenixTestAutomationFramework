package com.api.tests;

import static com.api.constant.Roles.FD;

import static com.api.utils.SpecUtil.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.*;

import org.testng.annotations.Test;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	
	@Test
	public void createJobAPITest()
	{
		
		
		// createjob payload
		
		
		Customer customer = new Customer("Devi", "Pothabatthula", "9390007654", "", "devi2728@yopmail.com", "");

		System.out.println(customer.email_id());
		CustomerAddress CustomerAddress = new CustomerAddress("1-18", "Swamy nagar", "1st Street", "Indrapalem",
				"Kakinada", "533006", "India", "Andhra Pradesh");

		CustomerProduct customerProduct = new CustomerProduct("2025-09-30T18:30:00.000Z", "12345678998761",
				"53601225372851", "53601225372851", "2025-09-30T18:30:00.000Z", 1, 1);

		Problems problem = new Problems(6, "over heat");
		
		List<Problems> problems=new ArrayList();
		
		problems.add(problem);
		CreateJobPayload createJobPayload = new CreateJobPayload(1, 2, 1, 1, customer, CustomerAddress, customerProduct,
				problems);
		
		given()
		.spec(requestSpecwithAuth(FD,createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema"+File.separator+"CreateJobAPIResponseSchema.json"))
		.body("message", equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", equalTo(1))
		.body("data.job_number", startsWith("JOB_"));
		
	}

}
