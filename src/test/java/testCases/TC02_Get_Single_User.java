package testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC02_Get_Single_User extends TestBase {

	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	public void getSingleUser() {
		logger.info("**************** Started gelAllUses ******************");

		RestAssured.baseURI = "https://reqres.in";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/api/users" + empId);

	}

	@Test
	public void checkResponseBody() {
		logger.info("**************** Checking Response Body *****************");

		String responseBody = response.getBody().asString();
		logger.info("Response Body is :" + responseBody);
		Assert.assertEquals(responseBody.contains(empId), true);

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
		Assert.assertTrue(responseTime < 6000);
	}

	@AfterClass
	public void tearDown() {
		logger.info("**************** Finished TC02_Get_Single_User ******************");

	}
}
