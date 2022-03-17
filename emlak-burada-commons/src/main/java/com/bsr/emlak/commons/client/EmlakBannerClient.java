package com.bsr.emlak.commons.client;

import com.bsr.emlak.commons.dto.request.BannerRequestDTO;
import com.bsr.emlak.commons.dto.response.BannerResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "emlak-banner-service", url = "http://localhost:8082")
public interface EmlakBannerClient {

    @PostMapping(value = "/banner")
    BannerResponseDTO saveBanner(@RequestBody BannerRequestDTO request);
}
