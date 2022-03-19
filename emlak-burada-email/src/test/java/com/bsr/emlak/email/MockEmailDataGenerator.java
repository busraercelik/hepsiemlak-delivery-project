package com.bsr.emlak.email;

import com.bsr.emlak.commons.dto.request.EmailMessageRequestDTO;

import java.time.LocalDateTime;

public class MockEmailDataGenerator {

    /* replace with valid values to test email */
    public static final String FROM_EMAIL = "";
    public static final String EMAIL_PASSWORD = "";

    public static EmailMessageRequestDTO prepareEmailDTO() {
        return EmailMessageRequestDTO.builder()
                .toEmlakUserId(1L)
                .toEmail("bsr.ercelik@gmail.com")
                .fromEmail(FROM_EMAIL)
                .userName("Busra Ercelik")
                .subject("Add posted successfully")
                .body("Your advert has been posted")
                .sentTimeStamp(LocalDateTime.now())
                .build();
    }

}
