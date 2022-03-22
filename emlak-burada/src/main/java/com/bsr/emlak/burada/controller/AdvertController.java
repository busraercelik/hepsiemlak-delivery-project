package com.bsr.emlak.burada.controller;

import com.bsr.emlak.burada.service.AdvertService;
import com.bsr.emlak.commons.dto.request.AdvertRequestDTO;
import com.bsr.emlak.commons.entity.Advert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bsr.emlak.commons.constant.CommonConstants.EMLAK_USER_ID_HEADER_NAME;

@Slf4j
@RestController
public class AdvertController {

	private final AdvertService advertService;

	public AdvertController(AdvertService advertService) {
		this.advertService = advertService;
	}

	@GetMapping(value = "/adverts")
	public ResponseEntity<List<Advert>> getAllAdvert() {
		return new ResponseEntity<>(advertService.getAllAdverts(), HttpStatus.OK);
	}

	@PostMapping(value = "/advert")
	public ResponseEntity<Advert> createAdvert(
			@RequestBody AdvertRequestDTO request, @RequestHeader(value="emlak_user_id") String emlakUserId) {
		log.info("createAdvert --> User id : {}", emlakUserId);
		request.setUserId(Long.getLong(emlakUserId));
		return new ResponseEntity<>(advertService.createAdvert(request), HttpStatus.CREATED);
	}

	@GetMapping(value = "/advert/{advertNo}")
	public ResponseEntity<Advert> getAdvertByAdvertId(@PathVariable(required = false) long advertNo) {
		return new ResponseEntity<>(advertService.getAdvertByAdvertId(advertNo), HttpStatus.OK);
	}

}
