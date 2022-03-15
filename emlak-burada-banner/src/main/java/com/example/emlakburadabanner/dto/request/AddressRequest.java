package com.example.emlakburadabanner.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {
	private String city;
	private String district;
	private String addressDesc;

}
