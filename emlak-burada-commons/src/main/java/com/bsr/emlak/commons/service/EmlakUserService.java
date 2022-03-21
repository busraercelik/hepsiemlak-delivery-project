package com.bsr.emlak.commons.service;

import com.bsr.emlak.commons.dto.request.EmlakUserRequestDTO;
import com.bsr.emlak.commons.entity.EmlakUser;
import com.bsr.emlak.commons.entity.EmlakUserAuthentication;
import com.bsr.emlak.commons.exception.EmlakBuradaAppException;
import com.bsr.emlak.commons.repository.EmlakUserAuthenticationRepository;
import com.bsr.emlak.commons.repository.EmlakUserRepository;
import com.bsr.emlak.commons.transformers.AdvertTransformer;
import com.bsr.emlak.commons.transformers.EmlakUserTransformer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.bsr.emlak.commons.constant.ErrorCode.*;

@Service
@Slf4j
public class EmlakUserService {

    private final EmlakUserRepository emlakUserRepository;
    private final AdvertTransformer advertTransformer;
    private final EmlakUserTransformer emlakUserTransformer;
    private final EmlakUserAuthenticationRepository emlakUserAuthenticationRepository;

    @Autowired
    public EmlakUserService(EmlakUserRepository emlakUserRepository, AdvertTransformer advertTransformer, EmlakUserTransformer emlakUserTransformer, EmlakUserAuthenticationRepository emlakUserAuthenticationRepository) {
        this.emlakUserRepository = emlakUserRepository;
        this.advertTransformer = advertTransformer;
        this.emlakUserTransformer = emlakUserTransformer;
        this.emlakUserAuthenticationRepository = emlakUserAuthenticationRepository;
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

    @Transactional
    public EmlakUser signUpEmlakUser(EmlakUserRequestDTO emlakUserRequestDTO) {
        /* save authentication */
        if (ObjectUtils.isEmpty(emlakUserRequestDTO.getPasswordHash())) {
            throw EmlakBuradaAppException.builder()
                            .errorCode(PASSWORD_EMPTY)
                            .httpStatusCode(401)
                            .build();
        }
        EmlakUserAuthentication userAuthentication = new EmlakUserAuthentication();
        EmlakUser emlakUser = emlakUserTransformer.transform(emlakUserRequestDTO);
        userAuthentication.setEmlakUser(emlakUser);
        userAuthentication.setPasswordHash(emlakUserRequestDTO.getPasswordHash());
        emlakUserAuthenticationRepository.save(userAuthentication);

        /* save user */
        return emlakUserRepository.save(emlakUser);
    }

}
