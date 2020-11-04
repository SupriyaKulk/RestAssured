package practice;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateProject {
	/**
	 * getAll PRoject URI : http://localhost:8084/addProject;
	 */
	@Test
	public void createPRoject() {
	
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("projectId", "123");
			jsonObj.put("createdBy", "deepak");
			jsonObj.put("projectName", "Vodafone1234");
			jsonObj.put("status", "aaa");
			jsonObj.put("teamSize", 10);
		
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.contentType(ContentType.JSON);
		reqSpec.body(jsonObj.toJSONString());
		
		Response resp = reqSpec.when().post("http://localhost:8084/addProject");
		
		System.out.println(resp.prettyPrint());
		System.out.println(resp.getStatusCode());
		
	}
}
