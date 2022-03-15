package com.example.emlakburadaemail;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.mail.MessagingException;

import com.example.emlakburadaemail.config.EmailConfig;
import com.example.emlakburadaemail.service.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private EmailConfig config;

    @BeforeEach
    void setup() {

        Mockito.when(config.getSmtpServer()).thenReturn("smtp.gmail.com");
        Mockito.when(config.getSmtpPort()).thenReturn("587");
        Mockito.when(config.getFrom()).thenReturn("emlakburada.patika@gmail.com");
        Mockito.when(config.getUsername()).thenReturn("patika@patika.com");
        Mockito.when(config.getPassword()).thenReturn("EmlakBurada2022");

    }

    @Test
    void it_should_throw_exception() {

        assertThrows(MessagingException.class, () -> {
            emailService.send("cem@patika.com");
        });

    }

    @Test
    void it_should_save() {

        assertDoesNotThrow(() -> {
            emailService.send("cem@patika.com");
        });

        //verify(emailRepository).save(any());

    }

}