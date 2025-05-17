package week3.day1;

import io.restassured.RestAssured;

public class PrintRequestInConsole {

	public static void main(String[] args) {
		
		RestAssured.given()
		           .baseUri("https://dev265761.service-now.com")
		           .basePath("/api/now/table")
		           .auth()
		           .basic("admin", "d@9IvhOh5DR*")		
		           .log().all() // this method is used to print request log in console
		           .when()
		           .get("/incident")
		           .then()		           
		           .assertThat()
		           .statusCode(200);
		

	}

}