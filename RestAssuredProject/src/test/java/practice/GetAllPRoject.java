package practice;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetAllPRoject {
	
	/**
	 * getAll PRoject URI : http://localhost:8084/projects;
	 */
	@Test
	public void getAllProject() {
		//Selenium code
		Response resp = RestAssured.get("http://localhost:8084/projects");
		
		//System.out.println(resp.asString());
		resp.prettyPrint();
		
		int actualStatusCode = resp.getStatusCode();
		System.out.println(actualStatusCode);
		Assert.assertEquals(actualStatusCode, 200);
		
		
		String actualContentType = resp.getContentType();
		System.out.println(actualContentType);
		Assert.assertEquals(actualContentType, "application/json");
		
		System.out.println(resp.getHeaders());
		//Data base code
	}

}
