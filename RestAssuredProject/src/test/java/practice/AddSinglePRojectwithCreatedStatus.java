package practice;

import static io.restassured.RestAssured.given;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;
import com.rmgyantra.api.pojolib.Project;

import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class AddSinglePRojectwithCreatedStatus {
	
	
	@Test
	public void addSingleproject() throws Throwable {
		//create JSONObject using POJO class
		Project projObj = new Project("Deepak", "Airtel-5", "Created", 10);
		
        //verify business LAyer
		 Response resp = given()
					      .contentType(ContentType.JSON)
					      .body(projObj,ObjectMapperType.JACKSON_1)
					 .when()
					      .post("http://localhost:8084/addProject");
					  resp.then()
					      .assertThat().statusCode(201)
					      .assertThat().contentType(ContentType.JSON)
					      .log().all();
					  
					  String actSucMsg  = resp.jsonPath().get("msg");
					  String actProjectName  = resp.jsonPath().get("projectName");
					  String projectID  = resp.jsonPath().get("projectId");
					  
					 Assert.assertEquals(actProjectName, "Airtel-5");
					 Assert.assertEquals(actSucMsg, "Successfully Added");
		//verify business LAyer 
						Driver driverRef = new Driver();
						 DriverManager.registerDriver(driverRef);
						 Connection conn  = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
						 Statement stat = conn.createStatement();
						  String query = "select * from project";

						 ResultSet result = stat.executeQuery(query);
					     
								 while (result.next()) {
									 
									 if(result.getString(4).equals(actProjectName) ){
										 System.out.println("PRoject name is verified ==PASS");
										 
											 if(result.getString(5).equals("Created")){
												 System.out.println("PRoject status is verified ==PASS");
												
											 }
									 }
									 
								 }
									
									
										 
							

						  conn.close();
		 
	}

}
