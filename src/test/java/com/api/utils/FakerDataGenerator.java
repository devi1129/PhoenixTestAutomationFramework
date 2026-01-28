package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDataGenerator {

	private static Faker faker = new Faker(new Locale("en-IND"));
	private static final String COUNTRY = "India";

	private static final int MST_SERVICE_LOCATION_ID = 0;
	private static final int MST_PLATFORM_ID = 2;
	private static final int MST_WARRANTY_STATUS_ID = 1;
	private static final int MST_OEM_ID = 1;

	private static final int MODEL_ID = 1;

	private static final int PRODUCT_ID = 1;

	private static final int VALID_PROBLEMS_ID[]= {1,2,3,4,5,6,7,8,9,10,11,12,15,16,17,19,20,22,24,26,27,28,29};
	private FakerDataGenerator() {

	}

	public static CreateJobPayload generateFakeCreateJobData() {

		Customer customer = generateFakeCustomerData();

		CustomerAddress customerAddress = generateFakeCustomerAddressData();

		CustomerProduct customerProduct = generateFakeCustomerProduct();

		List<Problems> problemList = generateFakeProblemsList();

		return new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID, MST_WARRANTY_STATUS_ID, MST_OEM_ID,
				customer, customerAddress, customerProduct, problemList);

	}

	public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count) {

		List<CreateJobPayload> payloadlist = new ArrayList();

		for (int i = 0; i < count; i++) {
			Customer customer = generateFakeCustomerData();

			CustomerAddress customerAddress = generateFakeCustomerAddressData();

			CustomerProduct customerProduct = generateFakeCustomerProduct();

			List<Problems> problemList = generateFakeProblemsList();

			CreateJobPayload payload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID,
					MST_WARRANTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemList);
			payloadlist.add(payload);

		}
		return payloadlist.iterator();

	}

	private static List<Problems> generateFakeProblemsList() {
		Random random = new Random();
		
		int count=random.nextInt(3)+1;
		
		
		List<Problems> problemList= new ArrayList();
		String remark ;
		Problems problems ;
		int id;
		for(int i=0;i<count;i++)
		{
		remark = faker.lorem().sentence(5);
	

		 id = random.nextInt(VALID_PROBLEMS_ID.length); // it will generate from 0 to 25to make sure 0 not to come added 1
		
		
		problems = new Problems(VALID_PROBLEMS_ID[id], remark);

		

		problemList.add(problems);
		}

		return problemList;
	}

	private static CustomerProduct generateFakeCustomerProduct() {

		String dop = DateTimeUtil.getTimewithDaysAgo(10);
		String imeSerialNumber = faker.numerify("###############");
		String popUrl = faker.internet().url();
		return new CustomerProduct(dop, imeSerialNumber, imeSerialNumber, imeSerialNumber, popUrl, PRODUCT_ID,
				MODEL_ID);
	}

	private static CustomerAddress generateFakeCustomerAddressData() {
		String apartmentnumber = faker.numerify("###");
		String apartmentname = faker.address().streetName();
		String streetName = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pincode = faker.numerify("#####");

		String state = faker.address().state();

		return new CustomerAddress(apartmentnumber, apartmentname, streetName, landmark, area, pincode, COUNTRY, state);
	}

	private static Customer generateFakeCustomerData() {

		String fname = faker.name().firstName();
		String lname = faker.name().lastName();
		String mobileNumber = faker.numerify("9#########");
		String altMobileNumber = faker.numerify("9#########");
		String email = faker.internet().emailAddress();

		String altEmail = faker.internet().emailAddress();

		return new Customer(fname, lname, mobileNumber, altMobileNumber, email, altEmail);

	}

}
