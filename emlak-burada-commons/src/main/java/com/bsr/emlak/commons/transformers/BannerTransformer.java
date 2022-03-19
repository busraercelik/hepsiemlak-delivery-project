package com.bsr.emlak.commons.transformers;

import com.bsr.emlak.commons.dto.request.BannerRequestDTO;
import com.bsr.emlak.commons.dto.response.BannerResponseDTO;
import com.bsr.emlak.commons.entity.Advert;
import com.bsr.emlak.commons.entity.Banner;
import com.bsr.emlak.commons.repository.AdvertRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BannerTransformer {

    private final AdvertRepository advertRepository;

    @Autowired
    public BannerTransformer(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    public BannerResponseDTO convertToBannerResponse(Banner banner) {
        BannerResponseDTO response = new BannerResponseDTO();
        BeanUtils.copyProperties(banner, response);
        return response;
    }

    public Banner transform(BannerRequestDTO request){
        Optional<Advert> optionalAdvert = advertRepository.findByAdvertUUID(request.getAdvertUUID());
        optionalAdvert.orElseThrow(()-> new RuntimeException("No advert found for the given advert uuid.") );
        Advert ad = optionalAdvert.orElse(null);

        Banner banner = new Banner();

        banner.setId(ad.getId());
        banner.setAdvertUUID(ad.getAdvertUUID());
        banner.setTitle(ad.getTitle());
        banner.setPhoneNumber(ad.getPhoneNumber());

        banner.setCity(ad.getProperty().getAddress().getCity());
        banner.setDistrict(ad.getProperty().getAddress().getDistrict());

        banner.setImages(ad.getImages());
        banner.setGrossSquareMeter(ad.getProperty().getGrossSquareMeter());

        banner.setPropertyType(ad.getProperty().getPropertyType());
        banner.setCost(ad.getCost());

        banner.setCreatedAt(ad.getCreatedAt());
        banner.setCreatedBy(ad.getCreatedBy());

        return banner;
    }

}
