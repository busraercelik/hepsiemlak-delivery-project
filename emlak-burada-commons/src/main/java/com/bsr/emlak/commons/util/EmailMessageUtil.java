package com.bsr.emlak.commons.util;

import com.bsr.emlak.commons.dto.request.EmailMessageRequestDTO;
import com.bsr.emlak.commons.entity.EmlakUser;
import lombok.experimental.UtilityClass;


@UtilityClass
public class EmailMessageUtil {

    public EmailMessageRequestDTO getAdvertCreatedEmailMessage(String UUID, EmlakUser emlakUser) {

        EmailMessageRequestDTO emailMessageRequestDTO = EmailMessageRequestDTO
                .builder()
                .userName(emlakUser.getFullName())
                .toEmail(emlakUser.getEmail())
                .subject("Advert successfully created")
                .body(String.format("You successfully created advert with id: %s", UUID))
                .sentTimeStamp(emlakUser.getModifiedAt())
                .toEmlakUserId(emlakUser.getId())
                .build();
        return emailMessageRequestDTO;
    }
}
