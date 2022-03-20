package com.bsr.emlak.commons.service;

import com.bsr.emlak.commons.dto.request.EmlakUserRequestDTO;
import com.bsr.emlak.commons.entity.EmlakUser;
import com.bsr.emlak.commons.exception.EmlakBuradaAppException;
import com.bsr.emlak.commons.repository.EmlakUserRepository;
import com.bsr.emlak.commons.transformers.AdvertTransformer;
import com.bsr.emlak.commons.transformers.EmlakUserTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bsr.emlak.commons.constant.ErrorCode.INVALID_PROPERTY_TYPE;
import static com.bsr.emlak.commons.constant.ErrorCode.NO_ACTIVE_PACKAGES_FOUND;

@Service
@Slf4j
public class EmlakUserService {

    private final EmlakUserRepository emlakUserRepository;
    private final AdvertTransformer advertTransformer;
    private final EmlakUserTransformer emlakUserTransformer;

    @Autowired
    public EmlakUserService(EmlakUserRepository emlakUserRepository, AdvertTransformer advertTransformer, EmlakUserTransformer emlakUserTransformer) {
        this.emlakUserRepository = emlakUserRepository;
        this.advertTransformer = advertTransformer;
        this.emlakUserTransformer = emlakUserTransformer;
    }

    public List<EmlakUser> getAllEmlakUsers() {
        return emlakUserRepository.findAll();
    }

    public EmlakUser getEmlakUserById(long id) {
        EmlakUser emlakUser = emlakUserRepository.
                findById(id)
                .orElseThrow(() -> EmlakBuradaAppException.builder()
                        .errorCode(NO_ACTIVE_PACKAGES_FOUND)
                        .httpStatusCode(400)
                        .build() );
        return emlakUser;
    }

    public EmlakUser saveEmlakUser(EmlakUserRequestDTO emlakUserRequestDTO) {
        return emlakUserRepository.save(emlakUserTransformer.transform(emlakUserRequestDTO));
    }

}
