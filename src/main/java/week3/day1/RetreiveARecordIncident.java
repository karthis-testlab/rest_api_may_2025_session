package week3.day1;

import io.restassured.RestAssured;

public class RetreiveARecordIncident {

	public static void main(String[] args) {
		
		RestAssured.given()		
        .baseUri("https://dev265761.service-now.com")
        .basePath("/api/now/table")
        .pathParam("table_name", "incident")
        .pathParam("sysId", "46b66a40a9fe198101f243dfbc79033d")
        .auth()
        .basic("admin", "d@9IvhOh5DR*")
        .log().all()
        .when()
        .get("/{table_name}/{sysId}")
        .then()
        .log().all()
        .assertThat()
        .statusCode(200);

	}

}
