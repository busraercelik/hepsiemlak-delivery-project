package com.bsr.emlak.banner.service;

import java.util.List;
import java.util.stream.Collectors;

import com.bsr.emlak.commons.dto.request.BannerRequestDTO;
import com.bsr.emlak.commons.dto.response.BannerResponseDTO;
import com.bsr.emlak.commons.entity.Advert;
import com.bsr.emlak.commons.repository.AdvertRepository;
import com.bsr.emlak.commons.repository.BannerRepository;
import com.bsr.emlak.commons.transformers.BannerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerService {

	private final BannerRepository repository;
	private final AdvertRepository advertRepository;

	@Autowired
	public BannerService(BannerRepository repository, AdvertRepository advertRepository) {
		this.repository = repository;
		this.advertRepository = advertRepository;
	}

	public List<BannerResponseDTO> getAllBanners() {
		return repository.findAll().stream()
				.map(BannerTransformer::convertToBannerResponse)
				.collect(Collectors.toList());
	}

	public void saveBanner(BannerRequestDTO request) {
		Advert advert = advertRepository.findByAdvertUUID(request.getAdvertUUID());
		repository.save(BannerTransformer.transform(advert));
	}
}
