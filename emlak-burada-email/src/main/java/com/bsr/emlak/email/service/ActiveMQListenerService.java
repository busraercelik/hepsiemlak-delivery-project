package com.bsr.emlak.email.service;

import com.bsr.emlak.email.dto.EmailMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ActiveMQListenerService {

    @Autowired
    private EmailService emailService;

    //@JmsListener(destination = "${online.property.queue}")
    public void receiveMessage(EmailMessage message) {
        log.info(message.toString());
        emailService.send(message.getEmail());
    }

}
