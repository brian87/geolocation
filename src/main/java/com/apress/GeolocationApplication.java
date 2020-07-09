package com.apress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.xml.transform.StringResult;

import garage.services.geolocation.types.GetLocationRequest;

@SpringBootApplication
public class GeolocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeolocationApplication.class, args);
		GetLocationRequest request = new GetLocationRequest();
		request.setIp("190.234.150.194");
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("garage.services.geolocation.types");
		StringResult res = new StringResult();
		marshaller.marshal(request, res);
		System.out.println(res);
	}

}
