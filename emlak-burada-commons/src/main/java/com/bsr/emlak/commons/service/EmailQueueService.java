package com.bsr.emlak.commons.service;

import com.bsr.emlak.commons.config.RabbitMqConfig;
import com.bsr.emlak.commons.dto.request.EmailMessageRequestDTO;
import com.bsr.emlak.commons.entity.Advert;
import com.bsr.emlak.commons.util.EmailMessageUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailQueueService {

    private final RabbitMqConfig rabbitMqConfig;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public EmailQueueService(RabbitMqConfig rabbitMqConfig, RabbitTemplate rabbitTemplate) {
        this.rabbitMqConfig = rabbitMqConfig;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendAdvertCreatedEmail(Advert savedAdvert) {
        EmailMessageRequestDTO message = EmailMessageUtil.getAdvertCreatedEmailMessage(
                savedAdvert.getAdvertUUID(),
                savedAdvert.getPostedByEmlakUser()
        );
        rabbitTemplate.convertAndSend(rabbitMqConfig.getExchange(), rabbitMqConfig.getRoutingkey(), message);
    }
}
