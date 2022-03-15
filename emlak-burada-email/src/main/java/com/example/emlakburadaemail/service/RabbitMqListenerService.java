package com.example.emlakburadaemail.service;

import com.example.emlakburadaemail.dto.EmailMessage;
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

	@RabbitListener(queues = "${emlakburada.rabbitmq.queue}")
	public void receiveMessage(EmailMessage message) {
		log.info(message.toString());
		emailService.send(message.getEmail());
	}

}
