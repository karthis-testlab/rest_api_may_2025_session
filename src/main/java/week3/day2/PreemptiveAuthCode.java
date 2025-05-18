package week3.day2;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;

public class PreemptiveAuthCode {
	
	static String requestBody = """
			{
    "fields": {
        "project": {
            "id": "10003"
        },
        "issuetype": {
            "id": "10013"
        },
        "summary": "Create new user story for demo mar 2025 batch 1"
    }
}
			""";

	public static void main(String[] args) {
		
		given()
		.baseUri("https://karthikeselene.atlassian.net")
		.basePath("/rest/api/3")
		.auth()
		.preemptive()
		.basic("karthike.selene@gmail.com", "<jira-api-token>")
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.body(requestBody)
		.post("/issue")
		.then()
		.assertThat()
		.statusCode(201)
		.statusLine(containsString("Created"));

	}

}
