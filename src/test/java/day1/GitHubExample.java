package day1;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class GitHubExample {
@Test(enabled=true,description="Getting all respositories")
public void getAllRepo() {
	given()
    .auth()
    .oauth2("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")
.when()
     .get("https://api.github.com/user/repos")  
 .then()
     .log()
     .body()
     .statusCode(200)
     .time(Matchers.lessThan(2000L),TimeUnit.MILLISECONDS);
}

@Test(enabled=true,description="Adding respository")
public void addRepository() {
	JSONObject js= new JSONObject();
	js.put("name", "testing2");
	js.put("description", "I am creating testing2");
	js.put("homepage", "https://github.com/SoumiliDas");

	given()
    .auth()
    .oauth2("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")
.when()
     .get("https://api.github.com/user/repos")  
 .then()
     .log()
     .body()
     .statusCode(200)
     .time(Matchers.lessThan(5000L),TimeUnit.MILLISECONDS);
}
}