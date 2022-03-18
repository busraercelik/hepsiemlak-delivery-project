package com.bsr.emlak.commons.transformers;


import com.bsr.emlak.commons.dto.request.MessageRequestDTO;
import com.bsr.emlak.commons.dto.response.MessageResponseDTO;
import com.bsr.emlak.commons.entity.Message;
import org.modelmapper.ModelMapper;

public class MessageTransformer {

    private static ModelMapper modelMapper = new ModelMapper();

    public static class Request {
        public static MessageRequestDTO transform(Message message) {
            MessageRequestDTO requestDTO = new MessageRequestDTO();
            modelMapper.map(message, requestDTO);
            return requestDTO;
        }
    }

    public static class Response {
        public static MessageResponseDTO transform(Message message) {
            MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
            modelMapper.map(message, messageResponseDTO);
            return messageResponseDTO;
        }
    }


    public static Message transform(MessageRequestDTO messageRequestDTO) {
        Message message = new Message();
        modelMapper.map(messageRequestDTO, message);
        return message;
    }
}
