package com.apress.ws.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import garage.services.geolocation.types.GetLocation;
import garage.services.geolocation.types.GetLocationResponse;

@Endpoint
public class GeoLocationEndpoint {

	@PayloadRoot(namespace = "urn:garage:services:geolocation:types", localPart = "GetLocation")
	@ResponsePayload
	public GetLocationResponse getLocation(@RequestPayload GetLocation getLocationRequest) {
		System.out.println(getLocationRequest.getIp());
		GetLocationResponse response = new GetLocationResponse();
		response.setStatus("success");
		return response;
	}

}
