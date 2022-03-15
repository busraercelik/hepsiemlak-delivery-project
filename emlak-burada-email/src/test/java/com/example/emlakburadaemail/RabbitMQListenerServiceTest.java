package com.example.emlakburadaemail;

import com.example.emlakburadaemail.dto.EmailMessage;
import com.example.emlakburadaemail.service.EmailService;
import com.example.emlakburadaemail.service.RabbitMqListenerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class RabbitMQListenerServiceTest {

    @InjectMocks
    private RabbitMqListenerService rabbitMqListenerService;

    @Mock
    private EmailService emailService;

    @Test
    void receiveMessage() {
        rabbitMqListenerService.receiveMessage(prepareEmail());
        verify(emailService).send("abc@gmail.com");
    }

    EmailMessage prepareEmail() {
        EmailMessage message = new EmailMessage();
        message.setEmail("abc@gmail.com");
        return message;
    }
}
