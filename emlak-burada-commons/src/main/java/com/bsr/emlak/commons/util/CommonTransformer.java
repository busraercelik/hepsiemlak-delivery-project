package com.bsr.emlak.commons.util;

import com.bsr.emlak.commons.dto.request.AdvertRequest;
import com.bsr.emlak.commons.dto.request.PersonRequestDTO;
import com.bsr.emlak.commons.dto.response.AdvertResponseDTO;
import com.bsr.emlak.commons.dto.response.PersonResponseDTO;
import com.bsr.emlak.commons.entity.Advert;
import com.bsr.emlak.commons.entity.Person;
import com.bsr.emlak.commons.enums.PropertyType;
import com.bsr.emlak.commons.factory.PropertyFactory;

public class CommonTransformer {

    public static Person convertToPerson(PersonRequestDTO personRequestDTO) {
        Person person = new Person();
        person.setFirstName(personRequestDTO.getFirstName());
        person.setLastName(personRequestDTO.getLastName());
        person.setEmail(personRequestDTO.getEmail());
        person.setPersonType(personRequestDTO.getUserType());

        return person;
    }

    public static PersonResponseDTO convertToResponseDTO(Person person) {
        PersonResponseDTO personResponseDTO = new PersonResponseDTO();
        personResponseDTO.setFirstName(person.getFirstName());
        personResponseDTO.setLastName(person.getLastName());
        personResponseDTO.setEmail(person.getEmail());
        personResponseDTO.setMobileNo(person.getMobileNo());
        personResponseDTO.setFavouriteAdverts(person.getFavouriteAdverts());

        return personResponseDTO;
    }

    public static AdvertResponseDTO convertToAdvertResponse(Advert savedAdvert) {
        AdvertResponseDTO response = new AdvertResponseDTO();
        response.setTitle(savedAdvert.getTitle());
        response.setCost(savedAdvert.getCost());
        response.setAdvertNo(savedAdvert.getAdNo());

        return response;
    }

    public static Advert convertToAdvert(AdvertRequest request) {
        Advert advert = new Advert(PropertyFactory.getProperty(PropertyType.RESIDENTIAL), new Person(), new String[5]);
        advert.setTitle(request.getTitle());
        advert.setCost(request.getCost());

        return advert;
    }
}
