package com.apress.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apress.dto.LocationDTO;

@Service
public class LocationService {

	public LocationDTO getLocationByIp(String ip) {
		if(isPrivateIp(ip)) {
			ip = getPublicIp();
		}
		RestTemplate restTemplate = new RestTemplate();
		LocationDTO locationDTO  = restTemplate.getForObject("http://ip-api.com/json/" + ip, LocationDTO.class);
		return locationDTO;
	}

	private String getPublicIp() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject("https://api.ipify.org/", String.class);
	}

	private boolean isPrivateIp(String ip) {
		return ip.equalsIgnoreCase("0:0:0:0:0:0:0:1");
	}
	
}
