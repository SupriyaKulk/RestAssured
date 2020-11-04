package practice;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeletePRoject {
	
	/**
	 * getAll PRoject URI : http://localhost:8084/projects;
	 */
	@Test
	public void deletPRoject() {
		//Selenium code
		Response resp = RestAssured.delete("http://localhost:8084/projects/TY_PROJ_605");
		
		//System.out.println(resp.asString());
		resp.prettyPrint();
		

	}

}
