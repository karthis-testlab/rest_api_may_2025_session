package week3.day1;

import static io.restassured.RestAssured.given;

import java.io.File;

public class UpdateRecordRequestBodyAsFile {
	
	public static void main(String[] args) {
		
		File requstBody = new File("src/main/resources/Request_Payload/update_incident.json");
		
		given()
		.baseUri("https://dev265761.service-now.com")
		.basePath("/api/now/table")
		.pathParam("tableName", "incident")
		.pathParam("sysid", "652056f9c3e1221082c2feac050131bf")
		.auth()
		.basic("admin", "d@9IvhOh5DR*")
		.header("Content-Type", "application/json")
		.log().all()
		.when()
		.body(requstBody)
		.put("/{tableName}/{sysid}")
		.then()
		.log().all()
		.assertThat()
		.statusCode(200);

	}

}