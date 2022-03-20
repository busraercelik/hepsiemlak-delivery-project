package com.bsr.emlak.burada.service;


import com.bsr.emlak.commons.dto.request.AdvertRequestDTO;
import com.bsr.emlak.commons.entity.EmlakUser;
import com.bsr.emlak.commons.entity.UsageLeft;
import com.bsr.emlak.commons.exception.EmlakBuradaAppException;
import com.bsr.emlak.commons.repository.EmlakUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.bsr.emlak.commons.constant.ErrorCode.*;

@Service
@Slf4j
public class AdvertValidatorService {

    private final EmlakUserRepository emlakUserRepository;

    @Autowired
    public AdvertValidatorService(EmlakUserRepository emlakUserRepository) {
        this.emlakUserRepository = emlakUserRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void validateCreateAdvertRequest(AdvertRequestDTO requestDTO) {
        EmlakUser emlakUser = emlakUserRepository
                .findById(requestDTO.getUserId())
                .orElseThrow(() -> EmlakBuradaAppException.builder()
                        .errorCode(USER_NOT_FOUND)
                        .httpStatusCode(400)
                        .build());

        UsageLeft usageLeft = emlakUser.getUsageLeft();
        if (ObjectUtils.isEmpty(usageLeft)) {
            throw EmlakBuradaAppException.builder()
                    .errorCode(NO_ACTIVE_PACKAGES_FOUND)
                    .httpStatusCode(400)
                    .build();
        }
        log.info("User : {} adverts left : {} last date to post : {}",
                emlakUser.getId(), usageLeft.getAdvertsLeft(), usageLeft.getLastDate());

        if (usageLeft.getAdvertsLeft() < 1 || LocalDateTime.now().isAfter(usageLeft.getLastDate())) {
            throw EmlakBuradaAppException.builder()
                    .errorCode(PACKAGE_HAS_EXPIRED)
                    .httpStatusCode(400)
                    .build();
        }

    }
}
