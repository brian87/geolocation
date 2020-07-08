package com.apress.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(toBuilder = true)
@Setter
@Getter
public class LocationDTO {

	protected String status;

	protected String country;

	protected String city;

}
