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

public class TC03_Post_User_Record extends TestBase {

	RequestSpecification httpRequest;
	Response response;

	String empName = RestUtils.empName();
	String empJob = RestUtils.empJob();

	@SuppressWarnings("unchecked")
	@BeforeClass
	public void createEmployee() throws InterruptedException {
		logger.info("**************** Started Create Employee ******************");

		RestAssured.baseURI = "https://reqres.in";
		httpRequest = RestAssured.given();

		// JSONOBject is a class that represent a simple JSON. We can add Key-Value
		// pairs using put method
		// {name="harmi123, job="qa tester"}

		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("job", empJob);

		httpRequest.header("Content-Type", "application/json"); 

		httpRequest.body(requestParams.toString());

		response = httpRequest.request(Method.POST, "/api/users");

		Thread.sleep(5000);

	}

	@Test
	public void checkResponseBody() {
		String responseBody = response.getBody().asString();
		System.out.println("Response Body Is: " + responseBody);
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empJob), true);

	}

	@Test
	public void checkStatusCode() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);

	}

	@Test
	public void checkContentType() {
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json; charset=utf-8");

	}

	@AfterClass
	public void tearDown() {
		logger.info("**************** Finished Create Employee ******************");

	}

}
