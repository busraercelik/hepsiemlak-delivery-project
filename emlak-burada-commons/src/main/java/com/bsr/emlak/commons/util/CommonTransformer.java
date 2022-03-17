package com.bsr.emlak.commons.util;

import com.bsr.emlak.commons.dto.request.AdvertRequestDTO;
import com.bsr.emlak.commons.dto.request.EmlakUserRequestDTO;
import com.bsr.emlak.commons.entity.Advert;
import com.bsr.emlak.commons.entity.EmlakUser;
import com.bsr.emlak.commons.enums.PropertyType;
import com.bsr.emlak.commons.factory.PropertyFactory;

public class CommonTransformer {

    public static EmlakUser convertToPerson(EmlakUserRequestDTO emlakUserRequestDTO) {
        EmlakUser emlakUser = new EmlakUser();
        emlakUser.setFirstName(emlakUserRequestDTO.getFirstName());
        emlakUser.setLastName(emlakUserRequestDTO.getLastName());
        emlakUser.setEmail(emlakUserRequestDTO.getEmail());
        emlakUser.setUserType(emlakUserRequestDTO.getUserType());

        return emlakUser;
    }

    public static Advert convertToAdvert(AdvertRequestDTO request) {
        Advert advert = new Advert(PropertyFactory.getProperty(PropertyType.RESIDENTIAL), new EmlakUser());
        advert.setTitle(request.getTitle());
        advert.setCost(request.getCost());

        return advert;
    }
}
