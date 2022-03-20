package com.bsr.emlak.email;

import static com.bsr.emlak.email.MockEmailDataGenerator.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.bsr.emlak.commons.repository.EmailRepository;
import com.bsr.emlak.commons.transformers.EmailTransformer;
import com.bsr.emlak.email.config.EmailConfig;
import com.bsr.emlak.email.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class EmailServiceTest {

    @Value("${from}")
    private static String FROM_EMAIL;

    @InjectMocks
    private EmailService emailService;

    @Mock
    private EmailRepository emailRepository;
    @Mock
    private EmailConfig config;
    @Mock
    private EmailTransformer emailTransformer;

    @BeforeEach
    void setup() {
        log.info("Email --> {} ", FROM_EMAIL);
        Mockito.when(config.getSmtpServer()).thenReturn("smtp.gmail.com");
        Mockito.when(config.getSmtpPort()).thenReturn("587");
        Mockito.when(config.getFrom()).thenReturn(FROM_EMAIL);
        Mockito.when(config.getUsername()).thenReturn(FROM_EMAIL);
        Mockito.when(config.getPassword()).thenReturn(EMAIL_PASSWORD);
    }

    @Test
    @Disabled
    void testSuccessfulEmail() {
        assertDoesNotThrow(() -> {
            emailService.send(prepareEmailDTO());
        });
        verify(emailRepository).save(any());
    }

}