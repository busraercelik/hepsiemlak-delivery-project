package com.bsr.emlak.commons.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "email")
@Getter
@Setter
public class Email extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = EmlakUser.class)
    @JoinColumn(name = "to_emlak_user_id", referencedColumnName = "id")
    private EmlakUser toEmlakUser;
    private String fromEmail;
    private String subject;
    private String body;
    private LocalDateTime sentTimeStamp;

}
