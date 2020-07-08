package com.apress.ws.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.apress.dto.LocationDTO;
import com.apress.service.LocationService;
import com.apress.service.mappers.LocationMapper;

import garage.services.geolocation.types.GetLocation;
import garage.services.geolocation.types.GetLocationResponse;

@Endpoint
public class GeoLocationEndpoint {

	@Autowired
	private LocationService locationService;
	@Autowired
	private LocationMapper locationMapper;

	@PayloadRoot(namespace = "urn:garage:services:geolocation:types", localPart = "GetLocation")
	@ResponsePayload
	public GetLocationResponse getLocation(@RequestPayload GetLocation getLocationRequest) {

		LocationDTO locationDTO = locationService.getLocationByIp(getLocationRequest.getIp());
		GetLocationResponse response = locationMapper.toGetLocationResponse(locationDTO);

		return response;

	}

}
