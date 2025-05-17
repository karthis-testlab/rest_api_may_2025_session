package week3.day1;

import io.restassured.RestAssured;

public class FirstCodeInRestAssured {

	public static void main(String[] args) {
		
		// Way 1: Best Practice
		RestAssured.given()		
		           .baseUri("https://dev265761.service-now.com")
		           .basePath("/api/now/table")
		           .auth()
		           .basic("admin", "d@9IvhOh5DR*")
		           .when()
		           .get("/incident")
		           .then()
		           .assertThat()
		           .statusCode(200);
		
		// Way 2:
		RestAssured.given()
		           .auth()
		           .basic("admin", "d@9IvhOh5DR*")
		           .when()
		           .get("https://dev265761.service-now.com/api/now/table/incident")
		           .then()
		           .assertThat()
		           .statusCode(200);
		
		

	}	
	
}