package com.example.emlakburadabanner.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.emlakburadabanner.dto.request.AddressRequest;
import com.example.emlakburadabanner.dto.request.BannerRequest;
import com.example.emlakburadabanner.dto.response.BannerResponse;
import com.example.emlakburadabanner.model.Address;
import com.example.emlakburadabanner.model.Banner;
import com.example.emlakburadabanner.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerService {

	private final BannerRepository repository;

	@Autowired
	public BannerService(BannerRepository repository) {
		this.repository = repository;
	}

	public List<BannerResponse> getAllBanners() {
		return repository.findAll().stream()
				.map(TransformerService::convertToBannerResponse)
				.collect(Collectors.toList());
	}

	public void saveBanner(BannerRequest request) {
		repository.save(TransformerService.convertToBanner(request));
	}
}
