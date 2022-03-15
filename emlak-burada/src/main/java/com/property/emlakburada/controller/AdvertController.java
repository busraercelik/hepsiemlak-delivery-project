package com.property.emlakburada.controller;

import com.property.emlakburada.client.request.AdvertRequest;
import com.property.emlakburada.dto.response.AdvertResponseDTO;
import com.property.emlakburada.service.AdvertService;
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
	public ResponseEntity<List<AdvertResponseDTO>> getAllAdvert() {
		return new ResponseEntity<>(advertService.getAllAdverts(), HttpStatus.OK);
	}

	@PostMapping(value = "/advert")
	public ResponseEntity<AdvertResponseDTO> createAdvert(@RequestBody AdvertRequest request) {
		return new ResponseEntity<>(advertService.saveAdvert(request), HttpStatus.CREATED);
	}

	@GetMapping(value = "/advert/{advertNo}")
	public ResponseEntity<AdvertResponseDTO> getAdvertByAdvertId(@PathVariable(required = false) long advertNo) {
		return new ResponseEntity<>(advertService.getAdvertByAdvertId(advertNo), HttpStatus.OK);
	}

	@GetMapping(value = "/adverts/{personId}")
	public ResponseEntity<List<AdvertResponseDTO>> getFavouriteAdverts(@PathVariable long personId) {
		return new ResponseEntity<>(advertService.getAllFavouriteAdverts(personId) , HttpStatus.OK);
	}

}
