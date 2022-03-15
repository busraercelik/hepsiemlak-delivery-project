package com.property.emlakburada.dto.response;

import com.property.emlakburada.model.Person;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MessageResponseDTO {
    private long id;
    private String text;
    private LocalDateTime sentAt;
    private Person sentFrom;
    private Person sentTo;
}
