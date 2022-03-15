package com.property.emlakburada.service;

import com.property.emlakburada.client.request.AddressRequest;
import com.property.emlakburada.client.request.AdvertRequest;
import com.property.emlakburada.client.request.BannerRequest;
import com.property.emlakburada.dto.response.AdvertResponseDTO;
import com.property.emlakburada.model.Advert;
import com.property.emlakburada.model.Person;
import com.property.emlakburada.queue.EmailMessage;
import com.property.emlakburada.queue.QueueService;
import com.property.emlakburada.repository.AdvertRepository;
import com.property.emlakburada.repository.PersonRepository;
import com.property.emlakburada.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
				.map(Response::convertToAdvertResponse)
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
		return Response.convertToAdvertResponse(savedAdvert);
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
				.map(Response::convertToAdvertResponse)
				.orElse(null);
	}

	public List<AdvertResponseDTO> getAllFavouriteAdverts(long id) {
		Optional<Person> byId = personRepository.findById(id);

		return byId.map(person -> person.getFavouriteAdverts()
				.stream()
				.map(Response::convertToAdvertResponse)
				.collect(Collectors.toList())).orElse(null);
	}

}
