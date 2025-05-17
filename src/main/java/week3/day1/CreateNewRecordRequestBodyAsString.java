package week3.day1;

import static io.restassured.RestAssured.*;

public class CreateNewRecordRequestBodyAsString {

	public static void main(String[] args) {
		
		String requestBody = """				
				{
				"short_description": "RESTAPIMAY2025",
				"description": "Create a new record using POST method"
              }""";
		
		given()
		.baseUri("https://dev265761.service-now.com")
		.basePath("/api/now/table")
		.pathParam("tableName", "incident")
		.auth()
		.basic("admin", "d@9IvhOh5DR*")
		.header("Content-Type", "application/json")
		.log().all()
		.when()
		.body(requestBody)
		.post("/{tableName}")
		.then()
		.log().all()
		.assertThat()
		.statusCode(201);

	}

}
