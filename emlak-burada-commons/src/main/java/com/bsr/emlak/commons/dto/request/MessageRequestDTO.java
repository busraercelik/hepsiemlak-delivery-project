package com.bsr.emlak.commons.dto.request;

import com.bsr.emlak.commons.entity.Person;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageRequestDTO {
    private String text;
    private LocalDateTime sentAt;
    private Person sentFrom;
    private Person sentTo;
}
