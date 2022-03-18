package com.bsr.emlak.commons.transformers;

import com.bsr.emlak.commons.dto.request.BannerRequestDTO;
import com.bsr.emlak.commons.dto.response.BannerResponseDTO;
import com.bsr.emlak.commons.entity.Advert;
import com.bsr.emlak.commons.entity.Banner;
import org.springframework.beans.BeanUtils;

public class BannerTransformer {

    public static BannerResponseDTO convertToBannerResponse(Banner banner) {
        BannerResponseDTO response = new BannerResponseDTO();
        BeanUtils.copyProperties(banner, response);
        return response;
    }

    public static Banner transform(BannerRequestDTO request) {
        Banner banner = new Banner();
        BeanUtils.copyProperties(request, banner);
        return banner;
    }

    public static Banner transform(Advert advert){
        Banner banner = new Banner();

        banner.setId(advert.getId());
        banner.setAdvertUUID(advert.getAdvertUUID());
        banner.setTitle(advert.getTitle());
        banner.setPhoneNumber(advert.getPhoneNumber());

        banner.setCity(advert.getProperty().getAddress().getCity());
        banner.setDistrict(advert.getProperty().getAddress().getDistrict());

        banner.setImageList(advert.getImageList());
        banner.setGrossSquareMeter(advert.getProperty().getGrossSquareMeter());

        banner.setPropertyType(advert.getProperty().getPropertyType());
        banner.setCost(advert.getCost());

        banner.setCreatedAt(advert.getCreatedAt());
        banner.setCreatedBy(advert.getCreatedBy());

        return banner;
    }

}
