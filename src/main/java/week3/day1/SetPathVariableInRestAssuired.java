package week3.day1;

import io.restassured.RestAssured;

public class SetPathVariableInRestAssuired {
	
	public static void main(String[] args) {
		RestAssured.given()		
        .baseUri("https://dev265761.service-now.com")
        .basePath("/api/now/table")
        .pathParam("table_name", "change_request")
        .auth()
        .basic("admin", "d@9IvhOh5DR*")
        .log().all()
        .when()
        .get("/{table_name}")
        .then()
        .assertThat()
        .statusCode(200);
	}

}