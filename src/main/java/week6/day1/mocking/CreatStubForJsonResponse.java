package week6.day1.mocking;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class CreatStubForJsonResponse {

	public static void main(String[] args) {
		
		String jsonBody = """
				
				{				
				 "message": "Hello! Welcome to WrieMock"				
				}
				
				""";
		
		MappingBuilder requestMock = WireMock.get("/greetings/json");
		
		ResponseDefinitionBuilder responseMock = WireMock.aResponse()
				.withStatus(200)
				.withStatusMessage("message")
				.withBody(jsonBody)
				.withHeader("Content-Type", "application/json");
		
		WireMock.stubFor(requestMock.willReturn(responseMock));

	}

}
