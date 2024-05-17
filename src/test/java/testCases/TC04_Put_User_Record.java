package testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.RestUtils;

public class TC04_Put_User_Record extends TestBase {

	RequestSpecification httpRequest;
	Response response;

	String empName = RestUtils.empName();
	String empJob = RestUtils.empJob();

	@SuppressWarnings("unchecked")
	@BeforeClass
	public void updateUserRecore() throws InterruptedException {
		logger.info("********* Started TC03_Put_User_Record **********");

		RestAssured.baseURI = "https://reqres.in/api/users/2";
		httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("job", empJob);

		// add a header stating request body is json
		httpRequest.headers("Content-Type", "application/json");

		// add the json to the body of the request
		httpRequest.body(requestParams.toString());

		response = httpRequest.request(Method.PUT, "/api/user/" + empId);

		Thread.sleep(5);

	}

	@Test
	public void checkResponseBody() {

		logger.info("******** Checking Response Body **********");
		String responseBody = response.getBody().asString();
		logger.info("Response body is : " + responseBody);
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empJob), true);
	}

	@Test
	public void checkStatusCode() {

		logger.info("******* Cheking Status Code ********");
		int statusCode = response.getStatusCode();
		logger.info("Status COde is : " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void checkContentType() {

		logger.info("****** Checking Content Type ******");
		String contentType = response.header("Content-Type");
		logger.info("Content Type is :" + contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}

	@Test
	public void checkServerType() {

		logger.info("******* Checking Server Type *******");
		String serverType = response.header("Server");
		logger.info("Server Type Is: " + serverType);
		Assert.assertEquals(serverType, "cloudflare");
	}
	
	@AfterClass
	public void tearDown() {
		logger.info("******* Finished TC04_Put_User_Record ******");
	}

}
