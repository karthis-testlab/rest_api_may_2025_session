package week3.day1;

import io.restassured.RestAssured;

public class SetQueryParameterInRestAssured {

	public static void main(String[] args) {		
		
		RestAssured.given()		
        .baseUri("https://dev265761.service-now.com")
        .basePath("/api/now/table")
        .queryParam("sysparm_fields", "number,sys_id,description,short_description,category,active")
        .queryParam("sysparm_query", "active=false")
        .queryParam("sysparm_limit", "3")
        .auth()
        .basic("admin", "d@9IvhOh5DR*")
        .log().all()
        .when()
        .get("/incident")
        .then()
        .log().all()
        .assertThat()
        .statusCode(200);

	}

}
