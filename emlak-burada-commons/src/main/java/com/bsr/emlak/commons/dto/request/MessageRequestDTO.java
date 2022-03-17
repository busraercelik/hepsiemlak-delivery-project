package com.bsr.emlak.commons.dto.request;

import com.bsr.emlak.commons.entity.EmlakUser;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageRequestDTO {
    private String text;
    private LocalDateTime sentAt;
    private EmlakUser sentFrom;
    private EmlakUser sentTo;
}
