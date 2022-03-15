package com.property.emlakburada.client.response;

import com.property.emlakburada.client.request.AddressRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BannerResponse {

	private int advertNo;
	private String phone;
	private int total;
	private AddressRequest address;

}
