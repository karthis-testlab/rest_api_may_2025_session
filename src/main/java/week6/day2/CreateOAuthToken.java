package week6.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateOAuthToken {

	public static void main(String[] args) {
		
		RestAssured.given()
		           .baseUri("https://dev265761.service-now.com")
		           .basePath("/oauth_token.do")
		           .contentType(ContentType.URLENC)
		           .log().all()
		           .when()
		           .formParam("grant_type", "password")
		           .formParam("client_id", "9ea3bd7eb0e4434eac2811b441e63b4d")
		           .formParam("client_secret", "wj+RIFAWm^")
		           .formParam("username", "admin")
		           .formParam("password", "d@9IvhOh5DR*")
		           .post()
		           .then()
		           .log().all()
		           .assertThat()
		           .statusCode(200);
		           

	}

}