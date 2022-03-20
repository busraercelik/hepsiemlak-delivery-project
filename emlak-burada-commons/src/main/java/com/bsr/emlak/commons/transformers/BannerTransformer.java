package com.bsr.emlak.commons.transformers;

import com.bsr.emlak.commons.dto.request.BannerRequestDTO;
import com.bsr.emlak.commons.dto.response.BannerResponseDTO;
import com.bsr.emlak.commons.entity.Advert;
import com.bsr.emlak.commons.entity.Banner;
import com.bsr.emlak.commons.exception.EmlakBuradaAppException;
import com.bsr.emlak.commons.repository.AdvertRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.bsr.emlak.commons.constant.ErrorCode.ADVERT_NOT_FOUND;

@Component
public class BannerTransformer {

    private final AdvertRepository advertRepository;

    @Autowired
    public BannerTransformer(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    public static class Response {
        public static BannerResponseDTO transform(Banner banner) {
            BannerResponseDTO response = new BannerResponseDTO();
            BeanUtils.copyProperties(banner, response);
            response.setPostedDate(banner.getCreatedAt());
            return response;
        }
    }

    public static class Request {
        public static BannerRequestDTO transform(Advert advert) {
            BannerRequestDTO response = new BannerRequestDTO();
            response.setAdvertUUID(advert.getAdvertUUID());
            return response;
        }
    }

    public Banner transform(BannerRequestDTO request){
        Optional<Advert> optionalAdvert = advertRepository.findByAdvertUUID(request.getAdvertUUID());
        optionalAdvert.orElseThrow(()-> EmlakBuradaAppException.builder()
                .errorCode(ADVERT_NOT_FOUND.formatted(request.getAdvertUUID()))
                .httpStatusCode(400)
                .build());

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
