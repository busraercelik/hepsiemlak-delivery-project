package com.property.emlakburada.util;

import com.property.emlakburada.client.request.AdvertRequest;
import com.property.emlakburada.config.factory.PropertyFactory;
import com.property.emlakburada.dto.PersonRequestDTO;
import com.property.emlakburada.dto.PropertyType;
import com.property.emlakburada.dto.response.AdvertResponseDTO;
import com.property.emlakburada.dto.response.PersonResponseDTO;
import com.property.emlakburada.model.Advert;
import com.property.emlakburada.model.Person;

public class Response {

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
