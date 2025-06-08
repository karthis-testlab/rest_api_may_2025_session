package week6.day2;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GraphQLAPIInRestAssured {
	
	static String query = """
			
			query {
  viewer {
    login
    name    
    avatarUrl    
    repositories {
      totalCount
      totalDiskUsage
    }
    followers {
      totalCount      
    }
  }
}
			
			""";

	public static void main(String[] args) {
		
		RestAssured.given()
		           .baseUri("https://api.github.com")
		           .basePath("/graphql")
		           .contentType(ContentType.JSON)
		           .header("Authorization", "Bearer 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")
		           .log().ifValidationFails()
		           .when()
		           .body(convertQueryToJsonString(query))
		           .post()
		           .then()
		           .log().all()
		           .assertThat()
		           .statusCode(200);

	}
	
	static String convertQueryToJsonString(String query) {
		JSONObject json = new JSONObject();
		json.put("query", query);
		return json.toString();
	}

}