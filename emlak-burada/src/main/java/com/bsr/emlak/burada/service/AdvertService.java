package com.bsr.emlak.burada.service;

import com.bsr.emlak.commons.dto.request.AdvertRequestDTO;
import com.bsr.emlak.commons.entity.Advert;
import com.bsr.emlak.commons.entity.EmlakUser;
import com.bsr.emlak.commons.repository.AdvertRepository;
import com.bsr.emlak.commons.repository.EmlakUserRepository;
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

	@Autowired
	public AdvertService(AdvertRepository advertRepository, EmlakUserRepository emlakUserRepository) {
		this.advertRepository = advertRepository;
		this.emlakUserRepository = emlakUserRepository;
	}

	public List<Advert> getAllAdverts() {
		return new ArrayList<>(advertRepository.findAll());
	}

	// after advert is saved send mail to queue
	public Advert saveAdvert(AdvertRequestDTO request) {
		EmlakUser emlakUser = emlakUserRepository.getById(request.getUserId());

		Advert advert = new Advert();
		advert.setPostedBy(emlakUser);
		advert.setTitle(request.getTitle());
		advert.setCost(request.getCost());
		Advert savedAdvert = advertRepository.save(advert);

		//EmailMessage emailMessage = new EmailMessage("cemdrman@gmail.com");
		//queueService.sendMessage(emailMessage);
		// bannerClient.saveBanner(prepareSaveBannerRequest());
		//emlakBannerService.saveBanner(prepareSaveBannerRequest());
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
