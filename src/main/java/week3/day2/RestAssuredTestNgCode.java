package week3.day2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RestAssuredTestNgCode {
	
	String requestBody;
	
	@BeforeMethod
	public void beforeMethod() {
		requestBody = """				
				{
             "short_description": "RESTAPIMAY2025",
             "description": "Create a new record using POST method"
                }				
				""";
	}
	
	@Test
	public void createNewRecord() {
		given()
		.baseUri("https://dev265761.service-now.com")
		.basePath("/api/now/table")
		.pathParam("tableName", "incident")
		.auth()
		.basic("admin", "d@9IvhOh5DR*")
		.header("Content-Type", "application/json")
		.log().all()
		.when()
		.body(requestBody)
		.post("/{tableName}")
		.then()
		.log().all()
		.assertThat()
		.statusCode(201)
		.statusLine(containsString("Created"));		
	}

}