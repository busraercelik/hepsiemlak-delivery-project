package com.bsr.emlak.email.service;

import com.bsr.emlak.commons.dto.request.EmailMessageRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMqListenerService {

	private final EmailService emailService;

	@Autowired
	public RabbitMqListenerService(EmailService emailService) {
		this.emailService = emailService;
	}

	@RabbitListener(queues = "${rabbitmq.queue}")
	public void receiveMessage(EmailMessageRequestDTO message) {
		log.info("Received message from queue : {}",message);
		emailService.send(message);
	}

}
