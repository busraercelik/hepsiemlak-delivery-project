package com.bsr.emlak.email;

import com.bsr.emlak.email.service.EmailService;
import com.bsr.emlak.email.service.RabbitMqListenerService;
import com.bsr.emlak.email.dto.EmailMessage;
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
