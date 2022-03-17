package com.bsr.emlak.commons.dto.response;

import com.bsr.emlak.commons.entity.EmlakUser;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageResponseDTO {
    private long id;
    private String text;
    private LocalDateTime sentAt;
    private EmlakUser sentFrom;
    private EmlakUser sentTo;
}
