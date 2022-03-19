package com.bsr.emlak.commons.entity;

import lombok.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "message")
public class Message extends BaseEntity{

    private String text;
    @OneToOne(fetch = FetchType.EAGER)
    private EmlakUser sentFrom;
    @OneToOne(fetch = FetchType.EAGER)
    private EmlakUser sentTo;
}
