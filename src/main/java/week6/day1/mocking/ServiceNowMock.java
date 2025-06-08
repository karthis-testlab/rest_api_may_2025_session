package week6.day1.mocking;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

import io.restassured.RestAssured;

public class ServiceNowMock {
	
	WireMockServer mockSever = new WireMockServer();	
	
	String requestPaylod = """
			{
             "short_description": "RESTAPIMAY2025",
             "description": "Create a new record using POST method"
            }				
			""";
	
	@BeforeClass
	public void startServer() {
		mockSever.start();
		System.out.println("Mock Server Starts....");
	}
	
	@AfterClass
	public void stopServer() {
		mockSever.stop();
		System.out.println("Mock Server Stops.");
	}
	
	@BeforeMethod
	public void createStub() {		
		
		MappingBuilder requestMock = WireMock.post("/api/now/table/incident")
				.withBasicAuth("admin", "d@9IvhOh5DR*")
				.withHeader("Content-Type", WireMock.equalTo("application/json"))
		        .withRequestBody(WireMock.equalToJson(requestPaylod));
		
		ResponseDefinitionBuilder responseMock = WireMock.aResponse()
				.withStatus(201)
				.withStatusMessage("Created")
				.withBodyFile("mock-reponse.json")
				.withHeader("Content-Type", "application/json");
		
		mockSever.stubFor(requestMock.willReturn(responseMock));
	}
	
	@Test
	public void validateMockServer() {
		RestAssured.given()
		           .baseUri("http://localhost:8080")
		           .basePath("/api/now/table")		           
		           .auth()		    
		           .preemptive()
		           .basic("admin", "d@9IvhOh5DR*")
		           .header("Content-Type", "application/json")
		           .log().all()
		           .when()
		           .body(requestPaylod)
		           .post("/incident")
		           .then()
		           .log().all()
		           .statusCode(201);
		           
	}

}