package com.rmgyantra.api.SupriyaKulkarni;



import org.testng.annotations.Test;

import com.rmgyantra.api.genericUtils.BaseAPIClass;
import com.rmgyantra.api.pojolib.IEndPoints;
import com.rmgyantra.api.pojolib.Project;

import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;




import static io.restassured.RestAssured.*;

/**
 * 
 * 
 * @author Supriya Kulkarni
 *
 */

public class modifyProject extends  BaseAPIClass {

	@Test
	public void modifyTest() throws Throwable
	{
	
		Project poj= new Project("Supriya", "API", "Ongoing", 10);
		
		Response resp=given()
				.contentType(ContentType.JSON)
			.body(poj,ObjectMapperType.JACKSON_1)
		
		.when()
			.post(IEndPoints.addProj);
		
		
		
		
		
		resp.then()
			.assertThat().statusCode(201)
			.log().all();
		
		
		String actualProjectId=resp.jsonPath().get("projectId");
		
		//modify status to completed
		
		
		poj.setStatus("Completed");
		given()
			.contentType(ContentType.JSON)
			.body(poj,ObjectMapperType.JACKSON_1)
			
		.when()
			.put("/projects/"+actualProjectId)
			
		.then()
			.log().all();
		
		dblib.executeQueryVerifyAndGetData("select * from project", 5, "Completed");
		
	
		
		
		
		
		
		
		
	}
	
	
}