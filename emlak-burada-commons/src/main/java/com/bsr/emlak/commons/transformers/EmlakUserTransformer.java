package com.bsr.emlak.commons.transformers;

import com.bsr.emlak.commons.dto.request.EmlakUserRequestDTO;
import com.bsr.emlak.commons.entity.EmlakUser;
import org.springframework.stereotype.Component;

@Component
public class EmlakUserTransformer {

    public EmlakUser transform(EmlakUserRequestDTO emlakUserRequestDTO) {
        EmlakUser emlakUser = new EmlakUser();
        emlakUser.setFirstName(emlakUserRequestDTO.getFirstName());
        emlakUser.setLastName(emlakUserRequestDTO.getLastName());
        emlakUser.setEmail(emlakUserRequestDTO.getEmail());
        emlakUser.setUserType(emlakUserRequestDTO.getUserType());
        emlakUser.setMobileNo(emlakUserRequestDTO.getMobileNo());

        return emlakUser;
    }
}
