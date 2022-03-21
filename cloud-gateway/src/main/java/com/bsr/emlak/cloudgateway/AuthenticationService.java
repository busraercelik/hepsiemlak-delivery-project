package com.bsr.emlak.cloudgateway;

import com.bsr.emlak.commons.dto.request.LoginRequestDTO;
import com.bsr.emlak.commons.exception.EmlakBuradaAppException;
import com.bsr.emlak.commons.repository.EmlakUserAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.bsr.emlak.commons.constant.ErrorCode.AUTHENTICATION_FAILED;
@Service
public class AuthenticationService {

    private final EmlakUserAuthenticationRepository emlakUserAuthenticationRepository;

    @Autowired
    public AuthenticationService(EmlakUserAuthenticationRepository emlakUserAuthenticationRepository) {
        this.emlakUserAuthenticationRepository = emlakUserAuthenticationRepository;
    }

    // email, password
    // create jwt and give back as response
    public String authenticate(LoginRequestDTO requestDTO) {
        emlakUserAuthenticationRepository
                .findByEmlakUser_EmailAndPasswordHash(requestDTO.getEmail(), requestDTO.getPasswordHash())
                .orElseThrow(() -> EmlakBuradaAppException.builder()
                        .errorCode(AUTHENTICATION_FAILED)
                        .httpStatusCode(401)
                        .build());
        return "Successfully login";
    }

}
