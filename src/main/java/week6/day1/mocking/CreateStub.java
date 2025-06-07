package week6.day1.mocking;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class CreateStub {
	
	public static void main(String[] args) {
		
		// Request Mocking
		MappingBuilder requestMock = WireMock.get("/greetings");
		
		// Response Mocking
		ResponseDefinitionBuilder responseMock = WireMock.aResponse()
				.withStatus(200)
				.withBody("Hello! Welcome to WrieMock");
		
		//Creating Stub
		WireMock.stubFor(requestMock.willReturn(responseMock));
		
		
	}

}