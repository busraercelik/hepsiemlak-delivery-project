package com.example.emlakburadaemail.service;

import com.example.emlakburadaemail.dto.EmailMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
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
