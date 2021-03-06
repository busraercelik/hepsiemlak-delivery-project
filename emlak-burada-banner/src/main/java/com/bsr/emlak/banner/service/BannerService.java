package com.bsr.emlak.banner.service;

import java.util.List;
import java.util.stream.Collectors;

import com.bsr.emlak.commons.dto.request.BannerRequestDTO;
import com.bsr.emlak.commons.dto.response.BannerResponseDTO;
import com.bsr.emlak.commons.entity.Banner;
import com.bsr.emlak.commons.repository.BannerRepository;
import com.bsr.emlak.commons.transformers.BannerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerService {

	private final BannerRepository repository;
	private final BannerTransformer bannerTransformer;

	@Autowired
	public BannerService(BannerRepository repository, BannerTransformer bannerTransformer) {
		this.repository = repository;
		this.bannerTransformer = bannerTransformer;
	}

	public List<BannerResponseDTO> getAllBanners() {
		return repository.findAll().stream()
				.map(BannerTransformer.Response::transform)
				.collect(Collectors.toList());
	}

	public Banner saveBanner(BannerRequestDTO request) {
		return repository.save(bannerTransformer.transform(request));
	}
}
