package com.example.emlakburadabanner.controller;

import java.util.List;

import com.example.emlakburadabanner.dto.request.BannerRequest;
import com.example.emlakburadabanner.dto.response.BannerResponse;
import com.example.emlakburadabanner.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BannerController {

	@Autowired
	private BannerService service;

	@GetMapping(value = "/banners")
	public ResponseEntity<List<BannerResponse>> getAllBanners() {
		return new ResponseEntity<>(service.getAllBanners(), HttpStatus.OK);
	}

	@PostMapping(value = "/banner")
	public ResponseEntity<?> saveBanner(@RequestBody BannerRequest request) {
		service.saveBanner(request);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
