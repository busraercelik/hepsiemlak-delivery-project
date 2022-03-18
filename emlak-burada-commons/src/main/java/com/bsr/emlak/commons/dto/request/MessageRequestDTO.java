package com.bsr.emlak.commons.dto.request;

import com.bsr.emlak.commons.entity.EmlakUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MessageRequestDTO {
    private String text;
    private LocalDateTime sentAt;
    private EmlakUser sentFrom;
    private EmlakUser sentTo;
}
