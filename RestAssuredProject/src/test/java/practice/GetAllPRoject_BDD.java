package practice;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

public class GetAllPRoject_BDD {
	
	/**
	 * getAll PRoject URI : http://localhost:8084/projects;
	 */
	@Test
	public void getAllProject() {
	    Response resp= when()
	       .get("http://localhost:8084/projects");
	    
	    
	    
	    ArrayList<String> al= resp.jsonPath().get("projectName");
	    for(String s:al)
	    {
	    	if(s.equalsIgnoreCase("API"))
	    	{
	    		System.out.println("s");
	    		break;
	    	}
	    }
	    
	   resp.then().log().all();
	      
	} 

}
