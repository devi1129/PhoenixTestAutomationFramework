package com.api.tests;

import static com.api.constant.Roles.FD;
import static com.api.utils.SpecUtil.requestSpecwithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.api.POJO.CreateJobPayload;
import com.api.POJO.Customer;
import com.api.POJO.CustomerAddress;
import com.api.POJO.CustomerProduct;
import com.api.POJO.Problems;

public class CreateJobAPITest {
	
	@Test
	public void createJobAPITest()
	{
		
		
		// createjob payload
		
		
		Customer customer = new Customer("Devi", "Pothabatthula", "9390007654", "", "devi2728@yopmail.com", "");

		CustomerAddress CustomerAddress = new CustomerAddress("1-18", "Swamy nagar", "1st Street", "Indrapalem",
				"Kakinada", "533006", "India", "Andhra Pradesh");

		CustomerProduct customerProduct = new CustomerProduct("2025-09-30T18:30:00.000Z", "12345678998761",
				"60601225372851", "60601225372851", "2025-09-30T18:30:00.000Z", 1, 1);

		Problems problem = new Problems(6, "over heat");
		Problems[] problems = new Problems[1];

		problems[0] = problem;
		CreateJobPayload createJobPayload = new CreateJobPayload(1, 2, 1, 1, customer, CustomerAddress, customerProduct,
				problems);
		
		given()
		.spec(requestSpecwithAuth(FD,createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(responseSpec_OK());
	}

}
