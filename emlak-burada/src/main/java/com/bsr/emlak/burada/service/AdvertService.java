package com.bsr.emlak.burada.service;

import com.bsr.emlak.commons.dto.response.AdvertResponseDTO;
import com.bsr.emlak.commons.entity.Advert;
import com.bsr.emlak.commons.entity.Person;
import com.bsr.emlak.burada.client.request.AddressRequest;
import com.bsr.emlak.burada.client.request.AdvertRequest;
import com.bsr.emlak.burada.client.request.BannerRequest;
import com.bsr.emlak.commons.repository.AdvertRepository;
import com.bsr.emlak.commons.repository.PersonRepository;
import com.bsr.emlak.commons.util.CommonTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdvertService {

	private final AdvertRepository advertRepository;
	private final PersonRepository personRepository;
	private int advertNo = 38164784;
	private int adNo = 1;

	@Autowired
	public AdvertService(AdvertRepository advertRepository, PersonRepository personRepository) {
		this.advertRepository = advertRepository;
		this.personRepository = personRepository;
	}

	public List<AdvertResponseDTO> getAllAdverts() {
		return advertRepository.findAll()
				.stream()
				.map(CommonTransformer::convertToAdvertResponse)
				.collect(Collectors.toList());
	}

	// after advert is saved send mail to queue
	public AdvertResponseDTO saveAdvert(AdvertRequest request) {
		Person person = personRepository.getById(request.getUserId());

		Advert advert = new Advert();
		advert.setAdNo(advertNo++);
		advert.setPostedBy(person);
		advert.setTitle(request.getTitle());
		advert.setCost(request.getCost());
		Advert savedAdvert = advertRepository.save(advert);

		//EmailMessage emailMessage = new EmailMessage("cemdrman@gmail.com");
		//queueService.sendMessage(emailMessage);
		// bannerClient.saveBanner(prepareSaveBannerRequest());
		//emlakBannerService.saveBanner(prepareSaveBannerRequest());
		return CommonTransformer.convertToAdvertResponse(savedAdvert);
	}

	private BannerRequest prepareSaveBannerRequest() {
		BannerRequest request = new BannerRequest();
		request.setAdvertNo(adNo++);
		request.setPhone("12345");
		request.setDuration(1);
		request.setAddress(new AddressRequest("istanbul", "Kadikoy", "home address"));

		return request;
	}


	public AdvertResponseDTO getAdvertByAdvertId(long advertId) {
		return advertRepository.findById(advertId)
				.map(CommonTransformer::convertToAdvertResponse)
				.orElse(null);
	}

	public List<AdvertResponseDTO> getAllFavouriteAdverts(long id) {
		Optional<Person> byId = personRepository.findById(id);

		return byId.map(person -> person.getFavouriteAdverts()
				.stream()
				.map(CommonTransformer::convertToAdvertResponse)
				.collect(Collectors.toList())).orElse(null);
	}

}
