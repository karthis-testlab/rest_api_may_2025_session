package week3.day1;

import static io.restassured.RestAssured.*;

import java.io.File;

public class CreateNewRecordRequestBodyAsFile {

	public static void main(String[] args) {
		
		File requstBody = new File("src/main/resources/Request_Payload/create_incident.json");
		
		given()
		.baseUri("https://dev265761.service-now.com")
		.basePath("/api/now/table")
		.pathParam("tableName", "incident")
		.auth()
		.basic("admin", "d@9IvhOh5DR*")
		.header("Content-Type", "application/json")
		.log().all()
		.when()
		.body(requstBody)
		.post("/{tableName}")
		.then()
		.log().all()
		.assertThat()
		.statusCode(201);

	}

}
