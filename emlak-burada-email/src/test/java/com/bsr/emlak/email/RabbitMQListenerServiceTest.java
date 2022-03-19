package com.bsr.emlak.email;

import com.bsr.emlak.commons.dto.request.EmailMessageRequestDTO;
import com.bsr.emlak.email.service.EmailService;
import com.bsr.emlak.email.service.RabbitMqListenerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static com.bsr.emlak.email.MockEmailDataGenerator.prepareEmailDTO;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class RabbitMQListenerServiceTest {

    @InjectMocks
    private RabbitMqListenerService rabbitMqListenerService;

    @Mock
    private EmailService emailService;

    @Test
    public void receiveMessage() {
        EmailMessageRequestDTO emailMessageRequestDTO = prepareEmailDTO();
        rabbitMqListenerService.receiveMessage(emailMessageRequestDTO);
        verify(emailService).send(emailMessageRequestDTO);
    }



}
