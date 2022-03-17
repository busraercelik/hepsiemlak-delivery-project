package com.bsr.emlak.banner.controller;

import java.util.List;

import com.bsr.emlak.banner.service.BannerService;
import com.bsr.emlak.commons.dto.request.BannerRequestDTO;
import com.bsr.emlak.commons.dto.response.BannerResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class BannerController {

	@Autowired
	private BannerService service;

	@GetMapping(value = "/banners")
	public ResponseEntity<List<BannerResponseDTO>> getAllBanners() {
		return new ResponseEntity<>(service.getAllBanners(), HttpStatus.OK);
	}

	@RequestMapping(value = "/banner", method = RequestMethod.POST)
	public ResponseEntity<?> saveBanner(@RequestBody BannerRequestDTO request) {
		service.saveBanner(request);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
