package com.bsr.emlak.burada.service;

import com.bsr.emlak.commons.dto.request.MessageRequestDTO;
import com.bsr.emlak.commons.dto.response.MessageResponseDTO;
import com.bsr.emlak.commons.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<MessageResponseDTO> getAllMessages() {
       return messageRepository.findAll()
               .stream()
               .map(MessageTranformerService::convertToMessageResponseDTO)
               .collect(Collectors.toList());
    }

    public MessageResponseDTO getMessageById(long id) {
        return messageRepository.findById(id).map(MessageTranformerService::convertToMessageResponseDTO).orElse(null);
    }

    public void saveMessage(MessageRequestDTO messageRequestDTO) {
        messageRepository.save(MessageTranformerService.convertToMessage(messageRequestDTO));
    }

}
