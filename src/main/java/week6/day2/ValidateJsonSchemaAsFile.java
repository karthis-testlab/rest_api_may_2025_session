package week6.day2;

import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.module.jsv.JsonSchemaValidator;

public class ValidateJsonSchemaAsFile {	

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
		.statusCode(201)		
		.body(JsonSchemaValidator.matchesJsonSchema(new File("src/main/resources/json-schema.json")));
	}

}
