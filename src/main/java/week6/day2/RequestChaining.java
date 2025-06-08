package week6.day2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RequestChaining {
	
	String sysId;
	File create;
	File update;
	String token;
	Map<String, String> oauth_info = new HashMap<>();
	
	@BeforeSuite
	public void beforeSuite() {
		oauth_info.put("grant_type", "password");
		oauth_info.put("client_id", "9ea3bd7eb0e4434eac2811b441e63b4d");
		oauth_info.put("client_secret", "wj+RIFAWm^");
		oauth_info.put("username", "admin");
		oauth_info.put("password", "d@9IvhOh5DR*");
	}
	
	@BeforeClass
	public void createOAUTHToken() {
		token = RestAssured.given()
        .baseUri("https://dev265761.service-now.com")
        .basePath("/oauth_token.do")
        .contentType(ContentType.URLENC)
        .log().all()
        .when()
        .formParams(oauth_info)
        .post()
        .then()
        .log().all()
        .assertThat()
        .statusCode(200)
        .extract()
        .jsonPath()
        .getString("access_token");
	}
	
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
				.header("Authorization", "Bearer "+token)
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
		.header("Authorization", "Bearer "+token)
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
		.header("Authorization", "Bearer "+token)
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
		.header("Authorization", "Bearer "+token)
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
		.header("Authorization", "Bearer "+token)
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