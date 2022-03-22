package com.bsr.emlak.email;

import com.bsr.emlak.commons.dto.request.EmailMessageRequestDTO;

import java.time.LocalDateTime;

public class MockEmailDataGenerator {

    /* replace with valid values to test email */
    public static final String EMAIL_PASSWORD = "";

    public static EmailMessageRequestDTO prepareEmailDTO() {
        EmailMessageRequestDTO messageRequestDTO = EmailMessageRequestDTO.builder()
                .toEmail("bsr.ercelik@gmail.com")
                .userName("Busra Ercelik")
                .subject("Add posted successfully")
                .body("Your advert has been posted")
                .sentTimeStamp(LocalDateTime.now())
                .build();
        messageRequestDTO.setEmlakUserId(1L);
        return messageRequestDTO;
    }

}
