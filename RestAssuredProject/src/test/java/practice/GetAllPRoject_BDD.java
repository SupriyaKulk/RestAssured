package practice;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GetAllPRoject_BDD {
	
	/**
	 * getAll PRoject URI : http://localhost:8084/projects;
	 */
	@Test
	public void getAllProject() {
	     when()
	       .get("http://localhost:8084/projects")
	     .then()
	          .assertThat().statusCode(200)
	          .and()
	          .assertThat().contentType(ContentType.JSON);
	      
	      
	} 

}
