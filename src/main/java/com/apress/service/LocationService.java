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
		// if ip is "0:0:0:0:0:0:0:1" then find the hostAddress
		if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
			ip = getIpLocalHost(ip);
		}
		if (isPrivateIPAddress(ip)) {
			ip = restTemplate.getForObject("https://api.ipify.org/", String.class);
		}
		return restTemplate.getForObject("http://ip-api.com/json/" + ip, LocationDTO.class);
	}

	private String getIpLocalHost(String ip) {
		InetAddress inetAddress = null;
		try {
			inetAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return inetAddress.getHostAddress();
	}

	private static boolean isPrivateIPAddress(String ipAddress) {
		InetAddress ia = null;
		try {
			InetAddress ad = InetAddress.getByName(ipAddress);
			byte[] ip = ad.getAddress();
			ia = InetAddress.getByAddress(ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ia.isSiteLocalAddress();
	}

}
