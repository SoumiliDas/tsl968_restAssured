package day1;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;//we did static import 

public class PositiveTest {
//https://documenter.getpostman.com/view/401288/SWLceUSf?version=latest#1ea9f47d-65b6-47d9-af7d-25c4d09f0cf9
	String s;
	@Test(enabled=false,description="for getting all the contacts list")
	public void getAllContactList(){
		
		//for getting all the contacts list
		given()
		.when()
		.get("http://3.13.86.142:3000/contacts")    /*https://documenter.getpostman.com/view/401288/SWLceUSf?version=latest*/
	.then()
	.log()
	.body()
	.statusCode(200);
	
	
	}
	
	@Test(enabled=true,description="to add contacts")
	public void addContact(){
		//using jason object to  store or give data
		//since location and employer are nested we created each object for it
		JSONObject loc=new JSONObject();
		loc.put("city", "Pune");
		loc.put("country", "India");
		
		JSONObject emp=new JSONObject();
		emp.put("jobTitle", "Automation tester");
		emp.put("company", "LTI");
		
		JSONObject body=new JSONObject();
		body.put("firstName", "Mayank");
		body.put("lastName", "Sharma");
		body.put("email", "mayank@lntinfotech.com");
		body.put("location", loc);
		body.put("employer", emp);
		
		
		//now send this above data
		
		//aLong with body send above details
		 s=given()
		    .header("Content-Type","application/json")  //content type is in the header of post man link*/
	        .body(body.toJSONString())  //to convert object in json type
	    .when()
	         .post("http://3.13.86.142:3000/contacts")  // send details to server so that time we use post request
	     .then()
	         .log()
	         .body()
	         .statusCode(200)
	         .extract()  // to extract id
	         .jsonPath()  // from json path
	         .get("_id");
	    System.out.println("id is : " + s);
	
	
	
	}
	
	@Test(enabled=true,dependsOnMethods="addContact",description="to get contacts")
	public void getContact(){
		given()
		.when()
		     .get("http://3.13.86.142:3000/contacts/" + s)
		.then()
		   .log()
		   .body()
		   .statusCode(200);
		
		
		
	}
	
	@Test(enabled=true,dependsOnMethods="getContact",description="to update contacts")
	public void updateContact(){
		//using jason object to  store or give data
		//since location and employer are nested we created each object for it
		JSONObject loc=new JSONObject();
		loc.put("city", "Pune");
		loc.put("country", "India");
		
		JSONObject emp=new JSONObject();
		emp.put("jobTitle", "Automation tester");
		emp.put("company", "LTI");
		
		JSONObject body=new JSONObject();
		body.put("firstName", "Soumili");
		body.put("lastName", "Das");
		body.put("email", "soumili@lntinfotech.com");
		body.put("location", loc);
		body.put("employer", emp);
		
		
		//now send this above data
		
		//aLong with body send above details
		 given()
		    .header("Content-Type","application/json")  //content type is in the header of post man link*/
	        .body(body.toJSONString())  //to convert object in json type
	    .when()
	         .put("http://3.13.86.142:3000/contacts/" + s)  // send details to server so that time we use post request
	     .then()
	         .log()
	         .body()
	         .statusCode(204);
	         
	    	
	
	
	}
	
	@Test(enabled=true,dependsOnMethods="updateContact",description="to delete contacts")
	public void deleteContact(){
		given()
		.when()
		     .delete("http://3.13.86.142:3000/contacts/" + s)
		.then()
		   .log()
		   .body()
		   .statusCode(204);
		
		
		
	}
	
	
	
	
	
}

