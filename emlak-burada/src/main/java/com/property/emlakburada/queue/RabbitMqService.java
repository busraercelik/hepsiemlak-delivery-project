package com.property.emlakburada.queue;

import com.property.emlakburada.config.RabbitMqConfig;
import com.property.emlakburada.service.EmailMessageService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService implements QueueService {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Autowired
	private RabbitMqConfig config;

	@Override
	public void sendMessage(EmailMessageService message) {
		rabbitTemplate.convertAndSend(config.getExchange(), config.getRoutingkey(), message);
	}
}
