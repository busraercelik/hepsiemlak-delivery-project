package com.bsr.emlak.commons.queue;

import com.bsr.emlak.commons.service.EmailQueueService;

public interface QueueService {
	
	void sendMessage(EmailQueueService email);

}
