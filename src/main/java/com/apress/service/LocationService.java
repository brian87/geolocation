package com.apress.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apress.dto.LocationDTO;

@Service
public class LocationService {

	public LocationDTO getLocationByIp(String ip) {
		String url = "http://ip-api.com/json/";
		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.getForObject(url + ip, LocationDTO.class);
	}
}
