package com.bsr.emlak.burada.service;

import com.bsr.emlak.commons.client.EmlakBannerClient;
import com.bsr.emlak.commons.dto.request.AdvertRequestDTO;
import com.bsr.emlak.commons.dto.request.BannerRequestDTO;
import com.bsr.emlak.commons.dto.response.BannerResponseDTO;
import com.bsr.emlak.commons.entity.Advert;
import com.bsr.emlak.commons.entity.EmlakUser;
import com.bsr.emlak.commons.repository.AdvertRepository;
import com.bsr.emlak.commons.repository.EmlakUserRepository;
import com.bsr.emlak.commons.service.EmailQueueService;
import com.bsr.emlak.commons.transformers.AdvertTransformer;
import com.bsr.emlak.commons.transformers.BannerTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AdvertService {

	private final AdvertRepository advertRepository;
	private final EmlakUserRepository emlakUserRepository;
	private final AdvertTransformer advertTransformer;
	private final EmlakBannerClient emlakBannerClient;
	private final EmailQueueService emailQueueService;

	@Autowired
	public AdvertService(AdvertRepository advertRepository, EmlakUserRepository emlakUserRepository,
						 AdvertTransformer advertTransformer, EmlakBannerClient emlakBannerClient,
						 EmailQueueService emailQueueService) {
		this.advertRepository = advertRepository;
		this.emlakUserRepository = emlakUserRepository;
		this.advertTransformer = advertTransformer;
		this.emlakBannerClient = emlakBannerClient;
		this.emailQueueService = emailQueueService;
	}

	public List<Advert> getAllAdverts() {
		List<Advert> allAdverts = advertRepository.findAll();
		return new ArrayList<>(allAdverts);
	}

	// after advert is saved send mail to queue
	public Advert saveAdvert(AdvertRequestDTO request) {
		Advert savedAdvert = advertRepository.save(advertTransformer.transform(request));
		/* push message to email topic */
		emailQueueService.sendAdvertCreatedEmail(savedAdvert);
		/* create banner using feign client */
		log.info("going to call banner service with advert uuid: {}", savedAdvert.getAdvertUUID());
		BannerRequestDTO bannerRequestDTO = BannerTransformer.Request.transform(savedAdvert);
		BannerResponseDTO bannerResponseDTO = emlakBannerClient.saveBanner(bannerRequestDTO);
		log.info("created banner with id {}", bannerResponseDTO.getAdvertUUID());
		return savedAdvert;
	}

	public Advert getAdvertByAdvertId(long advertId) {
		return advertRepository.findById(advertId)
				.orElse(null);
	}

	public List<Advert> getAllFavouriteAdverts(long id) {
		Optional<EmlakUser> byId = emlakUserRepository.findById(id);

		return byId.map(person -> new ArrayList<>(person.getFavouriteAdverts())).orElse(null);
	}

}
