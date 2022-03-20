package com.bsr.emlak.burada.queue;

import com.bsr.emlak.burada.service.EmailQueueService;

public interface QueueService {
	
	void sendMessage(EmailQueueService email);

}
