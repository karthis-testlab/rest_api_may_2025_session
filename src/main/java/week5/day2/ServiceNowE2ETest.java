package week5.day2;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class ServiceNowE2ETest extends RestAssuredBase {	
	
	@Test(priority = 1)
	public void create_a_record() {
		reqBuilder.addHeader("Content-Type", "application/json");
		sysId = given()	
				.spec(reqBuilder.build())								
				.when()
				.body(create)
				.post("/{tableName}")
				.then()		
				.spec(responseSpec(201, "Created", ContentType.JSON))				
				.extract()
				.jsonPath()		
				.getString("result.sys_id");
	}
	
	@Test(priority = 2)
	public void get_a_record() {
		reqBuilder.addPathParam("sys_id", sysId);
		given()		
		.spec(reqBuilder.build())			
		.when()
		.get("/{tableName}/{sys_id}")
		.then()		
		.spec(responseSpec(200, "OK", ContentType.JSON))
		.body("result.sys_id", equalTo(sysId))
		.contentType(ContentType.JSON);
	}
	
	@Test(priority = 3)
	public void update_a_record() {
		reqBuilder.addPathParam("sys_id", sysId);
		given()		
		.spec(reqBuilder.build())		
		.contentType(ContentType.JSON)		
		.when()
		.body(update)
		.put("/{tableName}/{sys_id}")
		.then()		
		.spec(responseSpec(200, "OK", ContentType.JSON))
		.body("result.sys_id", equalTo(sysId))		
		.contentType(ContentType.JSON);
	}
	
	@Test(priority = 4)
	public void delete_a_record() {
		reqBuilder.addPathParam("sys_id", sysId);
		given()		
		.spec(reqBuilder.build())				
		.when()
		.delete("/{tableName}/{sys_id}")
		.then()		
		.spec(responseSpec(204, "No Content"));
	}
	
	@Test(priority = 5)
	public void validateDeletion() {
		reqBuilder.addPathParam("sys_id", sysId);
		given()	
		.spec(reqBuilder.build())		
		.when()
		.get("/{tableName}/{sys_id}")
		.then()	
		.spec(responseSpec(404, "Not Found", ContentType.JSON));
	}

}