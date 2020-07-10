package com.apress.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apress.dto.LocationDTO;

@Service
public class LocationService {

	public LocationDTO getLocationByIp(String ip) {
		RestTemplate restTemplate = new RestTemplate();
		if (isPrivateIPAddress(ip)) {
			ip = restTemplate.getForObject("https://api.ipify.org/", String.class);
		}

		return restTemplate.getForObject("http://ip-api.com/json/" + ip, LocationDTO.class);
	}

	private static boolean isPrivateIPAddress(String ipAddress) {
		InetAddress ia = null;
		try {
			InetAddress ad = InetAddress.getByName(ipAddress);
			byte[] ip = ad.getAddress();
			ia = InetAddress.getByAddress(ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		}

		return ia.isSiteLocalAddress();
	}
}
