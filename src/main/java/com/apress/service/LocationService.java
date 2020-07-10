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
//		ip = getHostAddress(ip);
		if (isPrivateIPAddress(ip)) {
			ip = restTemplate.getForObject("https://api.ipify.org/", String.class);
		}

		return restTemplate.getForObject("http://ip-api.com/json/" + ip, LocationDTO.class);
	}

	private String getHostAddress(String ip) {
		InetAddress inetAddress = null;
		if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {

			try {
				inetAddress = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ipAddress = inetAddress.getHostAddress();
			ip = ipAddress;
		}
		return ip;
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
