package com.apress.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apress.dto.LocationDTO;

@Service
public class LocationService {

	public LocationDTO getLocationByIp(String ip) {
		if (isPrivateIp(ip) || ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
			ip = getPublicIp();
		}
		RestTemplate restTemplate = new RestTemplate();
		LocationDTO locationDTO = restTemplate.getForObject("http://ip-api.com/json/" + ip, LocationDTO.class);
		return locationDTO;
	}

	private String getPublicIp() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject("https://api.ipify.org/", String.class);
	}

	private boolean isPrivateIp(String ip) {
		InetAddress ia = null;
		try {
			InetAddress ad = InetAddress.getByName(ip);
			byte[] ipAddress = ad.getAddress();
			ia = InetAddress.getByAddress(ipAddress);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ia.isSiteLocalAddress();
	}

}
