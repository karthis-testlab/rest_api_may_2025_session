package week3.day2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import week3.day1.IncidentRequestPayload;

public class TestDataDrivenApproach {
	
	@DataProvider
	public String[][] testData() {
		return new String[][] {
			{"RESTAPIMAY2025 1", "Create a new record using POST method 1"},
			{"RESTAPIMAY2025 2", "Create a new record using POST method 2"},
		};
	}
	
	@Test(dataProvider = "testData")
	public void createNewRecord(String shortDescription, String description) {
		
		IncidentRequestPayload requestBody = new IncidentRequestPayload();
		
		requestBody.setShort_description(shortDescription);
		requestBody.setDescription(description);
		
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
		.statusLine(containsString("Created"));		
	}

}