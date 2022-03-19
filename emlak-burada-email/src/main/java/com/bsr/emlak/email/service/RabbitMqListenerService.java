package com.bsr.emlak.email.service;

import com.bsr.emlak.commons.dto.request.EmailMessageRequestDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RabbitMqListenerService {

	private final EmailService emailService;

	@Autowired
	public RabbitMqListenerService(EmailService emailService) {
		this.emailService = emailService;
	}

	@RabbitListener(queues = "${emlakburada.rabbitmq.queue:GENERAL_EMAIL_TOPIC}")
	public void receiveMessage(EmailMessageRequestDTO message) {
		log.info(message.toString());
		emailService.send(message);
	}

}
