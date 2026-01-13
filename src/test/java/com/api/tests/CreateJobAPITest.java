package com.api.tests;

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

public class CreateJobAPITest {
	
	 private CreateJobPayload createJobPayload;
	
	@BeforeMethod(description = "Creating createjob api request payload")
	public void setup()
	{
		
		Customer customer = new Customer("Devi", "Pothabatthula", "9390007654", "", "devi2728@yopmail.com", "");

		System.out.println(customer.email_id());
		CustomerAddress CustomerAddress = new CustomerAddress("1-18", "Swamy nagar", "1st Street", "Indrapalem",
				"Kakinada", "533006", "India", "Andhra Pradesh");

		CustomerProduct customerProduct = new CustomerProduct(getTimewithDaysAgo(10), "12345678998761",
				"09601225372851", "09601225372851", getTimewithDaysAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());

		Problems problem = new Problems(Problem.OVERHEATING.getCode(), "over heat");
		
		List<Problems> problems=new ArrayList();
		
		problems.add(problem);
		createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, CustomerAddress, customerProduct,
				problems);
		
	}
	
	@Test(description ="Verify if CreateJob API is able to create Inwarranty job",groups= {"api","regression","smoke"} )
	public void createJobAPITest()
	{
		
		
		// createjob payload
		
		
		
		
		
		
		given()
		.spec(requestSpecwithAuth(FD,createJobPayload))
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
