package com.bsr.emlak.burada.client;

import com.bsr.emlak.commons.dto.request.BannerRequestDTO;
import com.bsr.emlak.commons.dto.response.BannerResponseDTO;
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
		ResponseEntity<BannerResponseDTO> response = restTemplate
				.postForEntity(bannerServiceUrl, prepareSaveBannerRequest(), BannerResponseDTO.class);
		log.info("In saveBanner() response is  "+ response);

		if (response.getStatusCode() == HttpStatus.OK) {
			log.info(String.valueOf(response.getBody()));
		} else {
			log.info("Banner Service Status Code: "  + response.getStatusCode());
		}
	}

	private BannerRequestDTO prepareSaveBannerRequest() {
		BannerRequestDTO request = new BannerRequestDTO();
		request.setAdvertUUID("123y6");

		return request;
	}

}
