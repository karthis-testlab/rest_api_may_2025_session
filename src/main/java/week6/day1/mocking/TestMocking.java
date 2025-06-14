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

public class TestMocking {
	
	WireMockServer mockSever = new WireMockServer();
	
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
		String jsonBody = """
				
				{				
				 "message": "Hello! Welcome to WrieMock"				
				}
				
				""";
		
		MappingBuilder requestMock = WireMock.get("/greetings/json");
		
		ResponseDefinitionBuilder responseMock = WireMock.aResponse()
				.withStatus(200)
				.withStatusMessage("OK")
				.withBody(jsonBody)
				.withHeader("Content-Type", "application/json");
		
		mockSever.stubFor(requestMock.willReturn(responseMock));
	}
	
	@Test
	public void validateMockServer() {
		RestAssured.given()
		           .baseUri("http://localhost:8080")
		           .basePath("/greetings/json")
		           .log().all()
		           .when()
		           .get()
		           .then()
		           .log().all()
		           .statusCode(200);
		           
	}

}