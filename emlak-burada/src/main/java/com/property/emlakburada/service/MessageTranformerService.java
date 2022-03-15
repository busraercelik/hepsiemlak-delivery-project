package com.property.emlakburada.service;


import com.property.emlakburada.dto.MessageRequestDTO;
import com.property.emlakburada.dto.response.MessageResponseDTO;
import com.property.emlakburada.model.Message;
import org.modelmapper.ModelMapper;

public class MessageTranformerService {

    private static ModelMapper modelMapper = new ModelMapper();

    public static MessageResponseDTO convertToMessageResponseDTO(Message message) {
        MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
        modelMapper.map(message, messageResponseDTO);
        return messageResponseDTO;
    }

    public static MessageRequestDTO convertToMessageRequestDTO(Message message) {
        MessageRequestDTO requestDTO = new MessageRequestDTO();
        modelMapper.map(message, requestDTO);
        return requestDTO;
    }

    public static Message convertToMessage(MessageRequestDTO messageRequestDTO) {
        Message message = new Message();
        modelMapper.map(messageRequestDTO, message);

        return message;
    }
}
