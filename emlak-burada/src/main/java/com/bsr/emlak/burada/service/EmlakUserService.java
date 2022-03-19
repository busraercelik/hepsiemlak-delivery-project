package com.bsr.emlak.burada.service;

import com.bsr.emlak.commons.dto.request.EmlakUserRequestDTO;
import com.bsr.emlak.commons.entity.EmlakUser;
import com.bsr.emlak.commons.repository.EmlakUserRepository;
import com.bsr.emlak.commons.transformers.AdvertTransformer;
import com.bsr.emlak.commons.transformers.EmlakUserTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(() -> new RuntimeException(String.format("no person with given id %s found", id)));
        return emlakUser;
    }

    public void saveEmlakUser(EmlakUserRequestDTO emlakUserRequestDTO) {
        emlakUserRepository.save(emlakUserTransformer.transform(emlakUserRequestDTO));
    }

}
