package testCases;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC01_Get_Users extends TestBase {

	@BeforeClass
	public void getAllUser() {
		logger.info("**************** Started gelAllUses ******************");

		RestAssured.baseURI = "https://reqres.in";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/api/users?page=1");

	}

	@Test
	public void checkResponseBody() {
		logger.info("**************** Checking Response Body *****************");

		String responseBody = response.getBody().asString();
		logger.info("Response Body is :" + responseBody);
		Assert.assertTrue(responseBody != null);

	}

	@Test
	public void checkStatusCode() {
		logger.info("**************** Checking Status Code *****************");

		int statusCode = response.getStatusCode();
		logger.info("Status Code is :" + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void checkSResponseTime() {
		logger.info("**************** Checking Response Time *****************");

		long responseTime = response.getTime();
		logger.info("Response Time is :" + responseTime);

		if (responseTime > 2000) {
			logger.warn("Response time is greaterthen 2000");
			Assert.assertEquals(responseTime, 2000);

		}
	}
	
	@Test
	public void checkContentType() {
		logger.info("**************** Checking Content Type *****************");

		String contentType  = response.header("Content-Type");
		logger.info("Content Type is :" + contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");

	}
	
	//@Test
	public void checkContentLength() {
		logger.info("**************** Checking Content Length *****************");

		String contentLength  = response.header("Content-Length");
		logger.info("Content Length is :" + contentLength);

		if (Integer.parseInt(contentLength) <100) {
			logger.warn("Content Length is less than 100");
			Assert.assertTrue(Integer.parseInt(contentLength) >100);

		}
	}
	

	@Test
	public void checkDate() {
		logger.info("**************** Checking Date *****************");

		String date  = response.header("Date");
		logger.info("Date is :" + date);

		 // Get current date
	   // Date currentDate = new Date();
	    
	    //Assert.assertEquals(date, currentDate);
		
	}
	
	@AfterClass
	public void tearDown() {
		logger.info("**********  Finished TC01_Get_Users **************");
	}
}
