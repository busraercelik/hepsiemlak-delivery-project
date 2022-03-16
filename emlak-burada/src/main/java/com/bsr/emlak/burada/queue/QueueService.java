package com.bsr.emlak.burada.queue;

import com.bsr.emlak.burada.service.EmailMessageService;

public interface QueueService {
	
	void sendMessage(EmailMessageService email);

}
