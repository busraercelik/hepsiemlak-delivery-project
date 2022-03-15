package com.property.emlakburada.service;

import com.property.emlakburada.dto.MessageRequestDTO;
import com.property.emlakburada.dto.response.MessageResponseDTO;
import com.property.emlakburada.model.Message;
import com.property.emlakburada.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static com.property.emlakburada.service.MessageTranformerService.convertToMessage;

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
        messageRepository.save(convertToMessage(messageRequestDTO));
    }

}
