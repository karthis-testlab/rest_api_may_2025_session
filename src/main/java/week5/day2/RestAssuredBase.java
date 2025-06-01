package week5.day2;

import static io.restassured.RestAssured.basic;

import java.io.File;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class RestAssuredBase {
	
	protected String sysId;
	protected File create;
	protected File update;
	protected RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
	
	@BeforeClass
	public void beforeClass() {
		reqBuilder.setBaseUri("https://dev265761.service-now.com")
		          .setBasePath("/api/now/table")
		          .setAuth(basic("admin", "d@9IvhOh5DR*"))
		          .addPathParam("tableName", "incident")
		          .log(LogDetail.ALL);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		create = new File("src/main/resources/Request_Payload/create_incident.json");
		update = new File("src/main/resources/Request_Payload/update_incident.json");
	}
	
	protected ResponseSpecification responseSpec(int statusCode, String statusMessage, ContentType contentType) {
		return new ResponseSpecBuilder()
				   .expectStatusCode(statusCode)
				   .expectStatusLine(Matchers.containsString(statusMessage))
				   .expectContentType(contentType)
				   .log(LogDetail.STATUS)
				   .build();
	}
	
	protected ResponseSpecification responseSpec(int statusCode, String statusMessage) {
		return new ResponseSpecBuilder()
				   .expectStatusCode(statusCode)
				   .expectStatusLine(Matchers.containsString(statusMessage))				   
				   .log(LogDetail.STATUS)
				   .build();
	}

}