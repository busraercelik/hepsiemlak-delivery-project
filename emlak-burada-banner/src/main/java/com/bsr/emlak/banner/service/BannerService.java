package com.bsr.emlak.banner.service;

import java.util.List;
import java.util.stream.Collectors;

import com.bsr.emlak.commons.dto.request.BannerRequestDTO;
import com.bsr.emlak.commons.dto.response.BannerResponseDTO;
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

	public List<BannerResponseDTO> getAllBanners() {
		return repository.findAll().stream()
				.map(TransformerService::convertToBannerResponse)
				.collect(Collectors.toList());
	}

	public void saveBanner(BannerRequestDTO request) {
		repository.save(TransformerService.convertToBanner(request));
	}
}
