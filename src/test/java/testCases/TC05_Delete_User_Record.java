package testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC05_Delete_User_Record extends TestBase {

	RequestSpecification httpRequest;
	Response response;

	@BeforeClass
	public void deleteUser() throws InterruptedException {

		logger.info("******* Started TC05_Delete_User_Record ******");

		RestAssured.baseURI = "https://reqres.in";
		httpRequest = RestAssured.given();

		response = httpRequest.request(Method.GET, "/api/users?page=1");

		// First get the JsonPath Object instance from the Response interface
		JsonPath jsonpathElivator = response.jsonPath();
		// Capture id
		//String empID = jsonpathElivator.get("[0].id");
		//logger.info("EmpID is ---------- :" + empID);
		
		 // Extract the ID of the first user
    //    String empID = jsonpathElivator.get("$..data[0].id");
		
		
     //   int firstUserID = jsonpathElivator.getInt("$..data[0].id");

	//	logger.info("EmpID is ---------- :" + firstUserID);

		
		response = httpRequest.request(Method.DELETE, "/api/users/" + empId);

		//logger.info("----- getting id from RestUtilClass ----- " + empId);

		Thread.sleep(5);

	}

	@Test
	public void checkStatusCode() {

		int statusCode = response.getStatusCode();
		logger.info("***** Status code is : " + statusCode);

		Assert.assertEquals(statusCode, 204);

	}

	// @Test
	public void checkresponsebody() {

		String responseBody = response.getBody().asString();
		logger.info("Response body is : " + responseBody);
		Assert.assertEquals(responseBody.contains("Pass the response body message here"), true);

	}

	// @Test
	public void checkEndPoint() {
		logger.info("***** Checking Endpoint ******");

		String endPoint = response.header("Reporting-Endpoints");
		logger.info("End Point is : " + endPoint);
		Assert.assertEquals(endPoint,
				"heroku-nel=https://nel.heroku.com/reports?ts=1715370452&sid=c4c9725f-1ab0-44d8-820f-430df2718e11&s=e%2FSzh6o1LU7hob8hcv%2Bm8frBR3239Tvg1uf2BHOaRAY%3D");
	}

	@AfterClass
	public void tearDown() {

		logger.info("*************** Finished TC05_Delete_User_Record ****************");

	}

}
