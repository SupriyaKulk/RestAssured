package com.rmgyantra.api.projecttest;

import static io.restassured.RestAssured.given;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;
import com.rmgyantra.api.genericUtils.BaseAPIClass;
import com.rmgyantra.api.pojolib.IEndPoints;
import com.rmgyantra.api.pojolib.Project;

import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class DeleteProject extends BaseAPIClass{
	
	
	@Test
	public void addSingleproject() throws Throwable {
		//create JSONObject using POJO class
		Project projObj = new Project("Deepak", "Airtel-8", "Created", 10);

		 Response resp = given()
					      .contentType(ContentType.JSON)
					      .body(projObj,ObjectMapperType.JACKSON_1)
					 .when()
					      .post(IEndPoints.addProj);
					  resp.then()
					      .assertThat().statusCode(201)
					      .assertThat().contentType(ContentType.JSON)
					      .log().all();
					  
                       //get the response from  first request
					  String projectID  = resp.jsonPath().get("projectId");
					  
					  //pass data to another request
					given()
					  .contentType(ContentType.JSON)
					.when()
					   .pathParam("id", projectID)
					   .delete(IEndPoints.deleteProj+"/{id}")
					 .then()
					    .log().all()
					    .assertThat().statusCode(204);
					      
	}

}
