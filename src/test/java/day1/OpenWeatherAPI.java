package day1;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OpenWeatherAPI {

	@Test(enabled = false, description="Getting weather API information Generally")
  public void getWeatherInfo() {
/*	  Given > Precondition
	  When > User performs something
	  Then > expected outcome from system
*/
	  RestAssured.given()
	  				.when()
	  					.get("http://api.openweathermap.org/data/2.5/weather?q=Mumbai&appid=024ffda599b5fefdeed48f5f43c86ae4")
	  				.then()
	  					.log()
	  					.body()
	  					.statusCode(200);
  }
	
	@Test(enabled = false, description="Getting weather API information Generally")
	  public void getWeatherInfo2() {
		  Response res = RestAssured.given()
		  				.when()
		  					.get("http://api.openweathermap.org/data/2.5/weather?q=Mumbai&appid=024ffda599b5fefdeed48f5f43c86ae4");
		  
		  System.out.println(res.prettyPrint());
		  System.out.println(res.getTime());
		  System.out.println(res.getStatusCode());
		  System.out.println(res.getContentType());
	  }
	
	@Test(enabled = true, description="Getting weather API information Generally")
	  public void getWeatherInfo3() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("q", "Mumbai");
		param.put("appid", "024ffda599b5fefdeed48f5f43c86ae4");
		
		  RestAssured.given()
				  		//.queryParam("q", "Mumbai")
				  		//.queryParam("appid", "024ffda599b5fefdeed48f5f43c86ae4")
		  					.params(param)
		  				.when()
		  					.get("http://api.openweathermap.org/data/2.5/weather")
		  				.then()
		  					.log()
		  					.body()
		  					.statusCode(200);
	  }
}