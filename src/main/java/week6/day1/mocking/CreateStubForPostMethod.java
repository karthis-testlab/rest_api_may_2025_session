package week6.day1.mocking;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class CreateStubForPostMethod {

	public static void main(String[] args) {
		
		String requestPaylod = """
				{
				 "firstName": "Karthikeyan",
				 "lastName": "Rajendran"
				}
				""";
		
		String responsePayload = """
				{
				 "id": 1,
				 "firstName": "Karthikeyan",
				 "lastName": "Rajendran"
				}
				""";
		
		MappingBuilder requestMock = WireMock.post("/api/v1/create")
		        .withHeader("Content-Type", WireMock.equalTo("application/json"))
		        .withRequestBody(WireMock.equalToJson(requestPaylod));
		
	   ResponseDefinitionBuilder responseMock = WireMock.aResponse().withStatus(201)
	                       .withBody(responsePayload)
	                       .withHeader("Content-Type", "application/json");
	   
	   WireMock.stubFor(requestMock.willReturn(responseMock));

	}

}
