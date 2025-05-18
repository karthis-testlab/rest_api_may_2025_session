package week3.day2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class RequestChaining {
	
	String sysId;
	File create;
	File update;
	
	@BeforeMethod
	public void beforeMethod() {
		create = new File("src/main/resources/Request_Payload/create_incident.json");
		update = new File("src/main/resources/Request_Payload/update_incident.json");
	}
	
	@Test(priority = 1)
	public void create_a_record() {
		sysId = given()
				.baseUri("https://dev265761.service-now.com")
				.basePath("/api/now/table")
				.pathParam("tableName", "incident")
				.auth()
				.basic("admin", "d@9IvhOh5DR*")
				.header("Content-Type", "application/json")
				.log().all()
				.when()
				.body(create)
				.post("/{tableName}")
				.then()		
				.assertThat()
				.statusCode(201)
				.statusLine(containsString("Created"))
				.contentType(ContentType.JSON)				
				.extract()
				.jsonPath()		
				.getString("result.sys_id");
	}
	
	@Test(priority = 2)
	public void get_a_record() {
		given()
		.baseUri("https://dev265761.service-now.com")
		.basePath("/api/now/table")
		.pathParam("tableName", "incident")
		.pathParam("sys_id", sysId)
		.auth()
		.basic("admin", "d@9IvhOh5DR*")
		.log().all()
		.when()
		.get("/{tableName}/{sys_id}")
		.then()		
		.assertThat()
		.statusCode(200)
		.statusLine(containsString("OK"))
		.body("result.sys_id", equalTo(sysId))
		.contentType(ContentType.JSON);
	}
	
	@Test(priority = 3)
	public void update_a_record() {
		given()
		.baseUri("https://dev265761.service-now.com")
		.basePath("/api/now/table")
		.pathParam("tableName", "incident")
		.pathParam("sys_id", sysId)
		.auth()
		.basic("admin", "d@9IvhOh5DR*")
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.body(update)
		.put("/{tableName}/{sys_id}")
		.then()		
		.assertThat()
		.statusCode(200)
		.statusLine(containsString("OK"))
		.body("result.sys_id", equalTo(sysId))		
		.contentType(ContentType.JSON);
	}
	
	@Test(priority = 4)
	public void delete_a_record() {
		given()
		.baseUri("https://dev265761.service-now.com")
		.basePath("/api/now/table")
		.pathParam("tableName", "incident")
		.pathParam("sys_id", sysId)
		.auth()
		.basic("admin", "d@9IvhOh5DR*")
		.log().all()
		.when()
		.delete("/{tableName}/{sys_id}")
		.then()		
		.assertThat()
		.statusCode(204)
		.statusLine(containsString("No Content"));
	}
	
	@Test(priority = 5)
	public void validateDeletion() {
		given()
		.baseUri("https://dev265761.service-now.com")
		.basePath("/api/now/table")
		.pathParam("tableName", "incident")
		.pathParam("sys_id", sysId)
		.auth()
		.basic("admin", "d@9IvhOh5DR*")
		.log().all()
		.when()
		.get("/{tableName}/{sys_id}")
		.then()		
		.assertThat()
		.statusCode(404)
		.statusLine(containsString("Not Found"))
		.contentType(ContentType.JSON);
	}

}