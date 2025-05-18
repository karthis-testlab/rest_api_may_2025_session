package week3.day2;

import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExtractJsonArrayFromResponse {
	
	@Test
	public void extractJsonArray() {
		List<Map<String, Object>> list = given()
		.baseUri("https://dev265761.service-now.com")
		.basePath("/api/now/table")
		.pathParam("tableName", "incident")
		.auth()
		.basic("admin", "d@9IvhOh5DR*")
		.when()
		.get("/{tableName}")
		.then()
		.assertThat()
		.statusCode(200)
		.extract()
		.jsonPath()
		.getList("result");
		
		for (Map<String, Object> map : list) {
			if(map.get("category").toString().toLowerCase().equals("hardware")) {
				System.out.println(map.get("sys_id"));
			}
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> sysDomain = objectMapper.convertValue(list.get(0).get("sys_domain"), new TypeReference<Map<String, Object>>() {});
		// Map<String, Object> sysDomain = (Map<String, Object>) list.get(0).get("sys_domain");
		System.out.println(sysDomain.get("link"));
		System.out.println(sysDomain.get("value"));
	}

}