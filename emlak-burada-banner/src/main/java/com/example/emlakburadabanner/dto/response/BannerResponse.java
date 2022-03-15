package com.example.emlakburadabanner.dto.response;

import com.example.emlakburadabanner.dto.request.AddressRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BannerResponse {
	private int id;
	private int advertNo;
	private String phone;
	private int total;
	private AddressRequest address;
}
