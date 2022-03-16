package com.bsr.emlak.commons.client;

import com.bsr.emlak.commons.dto.request.BannerRequest;
import com.bsr.emlak.commons.dto.response.BannerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "emlak-banner-service", url = "http://localhost:8082")
public interface EmlakBannerClient {

    @PostMapping(value = "/banner")
    BannerResponse saveBanner(@RequestBody BannerRequest request);
}
