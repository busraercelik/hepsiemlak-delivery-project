package com.property.emlakburada.dto;

import com.property.emlakburada.model.Person;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageRequestDTO {
    private String text;
    private LocalDateTime sentAt;
    private Person sentFrom;
    private Person sentTo;
}
