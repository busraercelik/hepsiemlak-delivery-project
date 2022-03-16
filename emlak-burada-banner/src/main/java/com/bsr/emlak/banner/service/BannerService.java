package com.bsr.emlak.banner.service;

import java.util.List;
import java.util.stream.Collectors;

import com.bsr.emlak.commons.dto.request.BannerRequest;
import com.bsr.emlak.commons.dto.response.BannerResponse;
import com.bsr.emlak.commons.repository.BannerRepository;
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
