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

public class AddSinglePRojectwithOnGoingStatus extends BaseAPIClass{
	
	
	@Test
	public void addSingleproject() throws Throwable {
		//create JSONObject using POJO class
		Project projObj = new Project("Deepak", "Rel-1", "On Going", 10);
		
        //verify business LAyer
		 Response resp = given()
					      .contentType(ContentType.JSON)
					      .body(projObj,ObjectMapperType.JACKSON_1)
					 .when()
					     .post(IEndPoints.addProj);
					  resp.then()
					      .assertThat().statusCode(201)
					      .assertThat().contentType(ContentType.JSON)
					      .log().all();
					  
					  String actSucMsg  = resp.jsonPath().get("msg");
					  String actProjectName  = resp.jsonPath().get("projectName");
					  String projectID  = resp.jsonPath().get("projectId");
					  
					 Assert.assertEquals(actProjectName, "Rel-1");
					 Assert.assertEquals(actSucMsg, "Successfully Added");
					 dblib.executeQueryVerifyAndGetData("select * from project", 4, "Rel-1");
					 dblib.executeQueryVerifyAndGetData("select * from project", 5, "On Going");
		 
	}

}
