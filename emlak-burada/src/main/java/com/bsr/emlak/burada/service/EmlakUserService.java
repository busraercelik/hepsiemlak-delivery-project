package com.bsr.emlak.burada.service;

import com.bsr.emlak.commons.dto.request.EmlakUserRequestDTO;
import com.bsr.emlak.commons.entity.EmlakUser;
import com.bsr.emlak.commons.repository.EmlakUserRepository;
import com.bsr.emlak.commons.util.CommonTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmlakUserService {

    private final EmlakUserRepository emlakUserRepository;

    @Autowired
    public EmlakUserService(EmlakUserRepository emlakUserRepository) {
        this.emlakUserRepository = emlakUserRepository;
    }

    public List<EmlakUser> getAllUsers() {
        return emlakUserRepository.findAll();
    }

    public EmlakUser getPersonById(long id) {
        EmlakUser emlakUser = emlakUserRepository.
                findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("no person with given id %s found", id)));
        return emlakUser;
    }

    public void savePerson(EmlakUserRequestDTO emlakUserRequestDTO) {
        emlakUserRepository.save(CommonTransformer.convertToPerson(emlakUserRequestDTO));
    }

}
