package week3.day1;

import static io.restassured.RestAssured.*;

public class CreateNewRecordRequestBodyAsPojoObject {

	public static void main(String[] args) {
		
		// Plain Old Java Object (Serialisation, DeSerialisation)
		IncidentRequestPayload requstBody = new IncidentRequestPayload();
		requstBody.setDescription("Create a new record using POST method using POJO object");
		requstBody.setShort_description("RESTAPIMAY2025");
		
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
