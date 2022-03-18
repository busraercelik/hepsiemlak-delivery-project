package com.bsr.emlak.burada.controller;

import com.bsr.emlak.burada.service.AdvertService;
import com.bsr.emlak.commons.dto.request.AdvertRequestDTO;
import com.bsr.emlak.commons.entity.Advert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdvertController {

	@Autowired
	private AdvertService advertService;


	@GetMapping(value = "/adverts")
	public ResponseEntity<List<Advert>> getAllAdvert() {
		return new ResponseEntity<>(advertService.getAllAdverts(), HttpStatus.OK);
	}

	@PostMapping(value = "/advert")
	public ResponseEntity<Advert> createAdvert(@RequestBody AdvertRequestDTO request) {
		return new ResponseEntity<>(advertService.saveAdvert(request), HttpStatus.CREATED);
	}

	@GetMapping(value = "/advert/{advertNo}")
	public ResponseEntity<Advert> getAdvertByAdvertId(@PathVariable(required = false) long advertNo) {
		return new ResponseEntity<>(advertService.getAdvertByAdvertId(advertNo), HttpStatus.OK);
	}

	@GetMapping(value = "/adverts/{personId}")
	public ResponseEntity<List<Advert>> getFavouriteAdverts(@PathVariable long personId) {
		return new ResponseEntity<>(advertService.getAllFavouriteAdverts(personId) , HttpStatus.OK);
	}

}
