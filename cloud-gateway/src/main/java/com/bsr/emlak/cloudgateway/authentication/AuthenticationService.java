package com.bsr.emlak.cloudgateway.authentication;

import com.bsr.emlak.commons.constant.CommonConstants;
import com.bsr.emlak.commons.dto.request.LoginRequestDTO;
import com.bsr.emlak.commons.entity.EmlakUser;
import com.bsr.emlak.commons.entity.EmlakUserAuthentication;
import com.bsr.emlak.commons.exception.EmlakBuradaAppException;
import com.bsr.emlak.commons.repository.EmlakUserAuthenticationRepository;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.ECDSASigner;
import com.nimbusds.jose.crypto.ECDSAVerifier;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.gen.ECKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static com.bsr.emlak.commons.constant.ErrorCode.SECRET_KEY_NOT_CONFIGURED;

@Service
@Slf4j
public class AuthenticationService {

    private final EmlakUserAuthenticationRepository emlakUserAuthenticationRepository;
    @Value("#{environment.SECRET_KEY}")
    private String secretKey;
    private ECKey key;

    @PostConstruct
    @SneakyThrows
    public void detectSecretKey(){
        if(StringUtils.isEmpty(secretKey)){
            throw EmlakBuradaAppException.builder()
                    .errorCode(SECRET_KEY_NOT_CONFIGURED)
                    .build();
        }
        else{
            //Creating an EC Key
            this.key = new ECKeyGenerator(Curve.P_384)
                    .keyID(secretKey)
                    .generate();
        }
    }

    @Autowired
    public AuthenticationService(EmlakUserAuthenticationRepository emlakUserAuthenticationRepository) {
        this.emlakUserAuthenticationRepository = emlakUserAuthenticationRepository;
    }

    // email, password
    // create jwt and give back as response
    public AuthenticationResponseDTO authenticate(LoginRequestDTO requestDTO) {
        Optional<EmlakUserAuthentication> authenticationOptional = emlakUserAuthenticationRepository
                .findByEmlakUser_EmailAndPasswordHash(requestDTO.getEmail(), requestDTO.getPasswordHash());

        return authenticationOptional.isPresent() ? AuthenticationResponseDTO.builder()
                .isSuccessfulAuthentication(true)
                .message("Successfully login")
                .token(generateToken(authenticationOptional.get().getEmlakUser()))
                .build()
                : AuthenticationResponseDTO.builder()
                .isSuccessfulAuthentication(false)
                .message("Invalid credentials")
                .build();
    }

    @SneakyThrows
    private String generateToken(EmlakUser emlakUser) {

        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.ES384)
                .type(JOSEObjectType.JWT)
                .keyID(key.getKeyID())
                .build();

        JWTClaimsSet payload = new JWTClaimsSet.Builder()
                .issuer("ADMIN")
                .audience("API_USER")
                .subject(emlakUser.getId().toString())
                .claim("USERNAME", emlakUser.getFullName())
                .expirationTime(Date.from(Instant.now().plusSeconds(5*60)))
                .build();

        SignedJWT signedJWT = new SignedJWT(header, payload);
        signedJWT.sign(new ECDSASigner(key.toECPrivateKey()));
        String jwt = signedJWT.serialize();

        return jwt;
    }

    @SneakyThrows
    public boolean verifyToken(String jwt) {
        // Verifying a JWT Signature
        try {
            return SignedJWT.parse(jwt).verify(new ECDSAVerifier(key.toECPublicKey()));
        }
        catch (Exception e){
            log.error("Corrupted token {}", e.getMessage(), e);
            return false;
        }
    }
}
