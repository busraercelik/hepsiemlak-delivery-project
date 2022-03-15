package com.property.emlakburada.queue;

import com.property.emlakburada.service.EmailMessageService;

public interface QueueService {
	
	void sendMessage(EmailMessageService email);

}
