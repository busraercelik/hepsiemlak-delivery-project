package com.bsr.emlak.banner.service;

import com.bsr.emlak.commons.dto.request.BannerRequestDTO;
import com.bsr.emlak.commons.dto.response.BannerResponseDTO;
import com.bsr.emlak.commons.entity.Banner;
import org.springframework.beans.BeanUtils;

public class TransformerService {

    public static BannerResponseDTO convertToBannerResponse(Banner banner) {
        BannerResponseDTO response = new BannerResponseDTO();
        BeanUtils.copyProperties(banner, response);
        return response;
    }

    public static Banner convertToBanner(BannerRequestDTO request) {
        Banner banner = new Banner();
        BeanUtils.copyProperties(request, banner);
        return banner;
    }

}
