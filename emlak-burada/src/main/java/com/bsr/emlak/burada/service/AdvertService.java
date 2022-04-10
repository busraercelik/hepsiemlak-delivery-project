package com.bsr.emlak.burada.service;

import com.bsr.emlak.commons.client.EmlakBannerClient;
import com.bsr.emlak.commons.dto.request.AdvertRequestDTO;
import com.bsr.emlak.commons.dto.request.BannerRequestDTO;
import com.bsr.emlak.commons.dto.response.BannerResponseDTO;
import com.bsr.emlak.commons.entity.Advert;
import com.bsr.emlak.commons.entity.EmlakUser;
import com.bsr.emlak.commons.entity.UsageLeft;
import com.bsr.emlak.commons.repository.AdvertRepository;
import com.bsr.emlak.commons.repository.EmlakUserRepository;
import com.bsr.emlak.commons.service.EmailQueueService;
import com.bsr.emlak.commons.transformers.AdvertTransformer;
import com.bsr.emlak.commons.transformers.BannerTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

@Slf4j
@Service
public class AdvertService {

	private final AdvertRepository advertRepository;
	private final EmlakUserRepository emlakUserRepository;
	private final AdvertTransformer advertTransformer;
	private final EmlakBannerClient emlakBannerClient;
	private final EmailQueueService emailQueueService;
	private final AdvertValidatorService advertValidatorService;
	private final ExecutorService executorService;

	@Autowired
	public AdvertService(AdvertRepository advertRepository, EmlakUserRepository emlakUserRepository,
						 AdvertTransformer advertTransformer, EmlakBannerClient emlakBannerClient,
						 EmailQueueService emailQueueService, AdvertValidatorService advertValidatorService, ExecutorService executorService) {
		this.advertRepository = advertRepository;
		this.emlakUserRepository = emlakUserRepository;
		this.advertTransformer = advertTransformer;
		this.emlakBannerClient = emlakBannerClient;
		this.emailQueueService = emailQueueService;
		this.advertValidatorService = advertValidatorService;
		this.executorService = executorService;
	}

	public List<Advert> getAllAdverts() {
		List<Advert> allAdverts = advertRepository.findAll();
		return new ArrayList<>(allAdverts);
	}

	public Advert createAdvert(AdvertRequestDTO requestDTO) {
		/* validate the advert create request */
		advertValidatorService.validateCreateAdvertRequest(requestDTO);
		/* execute database operations for creating advert */
		Advert savedAdvert = saveAdvert(requestDTO);
		emailQueueService.sendAdvertCreatedEmail(savedAdvert);
		/* create banner using feign client */
		log.info("going to call banner service with advert uuid: {}", savedAdvert.getAdvertUUID());

		// it won't block my request thread
		executorService.execute(() -> {
			BannerRequestDTO bannerRequestDTO = BannerTransformer.Request.transform(savedAdvert);
			BannerResponseDTO bannerResponseDTO = emlakBannerClient.saveBanner(bannerRequestDTO);
			log.info("created banner with id {}", bannerResponseDTO.getAdvertUUID());
		});

		return savedAdvert;
	}

	// locking
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public Advert saveAdvert(AdvertRequestDTO request) {
		Advert savedAdvert = advertRepository.save(advertTransformer.transform(request));
		EmlakUser emlakUser = savedAdvert.getPostedByEmlakUser();
		UsageLeft usageLeft = emlakUser.getUsageLeft();
		usageLeft.setAdvertsLeft(usageLeft.getAdvertsLeft() - 1);
		log.info("User has {} adverts left", usageLeft.getAdvertsLeft());
		/* trigger a save on advert */
		advertRepository.save(savedAdvert);
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
