package com.bsr.emlak.burada.client;

import com.bsr.emlak.burada.client.request.AddressRequest;
import com.bsr.emlak.burada.client.request.BannerRequest;
import com.bsr.emlak.burada.client.response.BannerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class BannerClient {

	/**
	 * consuming the POST API response by using RestTemplate
	 */
	private int adNo = 1;
	public void saveBanner() {
		RestTemplate restTemplate = new RestTemplate();

		String bannerServiceUrl = "http://localhost:8081/banners";
		ResponseEntity<BannerResponse> response = restTemplate
				.postForEntity(bannerServiceUrl, prepareSaveBannerRequest(), BannerResponse.class);
		log.info("In saveBanner() response is  "+ response);

		if (response.getStatusCode() == HttpStatus.OK) {
			log.info(String.valueOf(response.getBody()));
		} else {
			log.info("Banner Service Status Code: "  + response.getStatusCode());
		}
	}

	private BannerRequest prepareSaveBannerRequest() {
		BannerRequest request = new BannerRequest();
		request.setAdvertNo(adNo++);
		request.setPhone("12345");
		request.setDuration(1);
		request.setAddress(new AddressRequest("istanbul", "Kadikoy", "home address"));

		return request;
	}

}
