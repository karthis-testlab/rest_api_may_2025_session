package week3.day1;

import io.restassured.RestAssured;

public class PrintResponseInConsole {

	public static void main(String[] args) {
		
		RestAssured.given()
		           .baseUri("https://dev265761.service-now.com")
		           .basePath("/api/now/table")
		           .auth()
		           .basic("admin", "d@9IvhOh5DR*")		          
		           .when()
		           .get("/incident")
		           .then()
		           .log().all() // this method is used to print response log in console
		           .assertThat()
		           .statusCode(200);
		

	}

}