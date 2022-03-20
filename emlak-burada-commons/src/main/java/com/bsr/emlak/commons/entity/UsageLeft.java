package com.bsr.emlak.commons.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "usage_left")
public class UsageLeft extends BaseEntity {
    private LocalDateTime lastDate;
    private Integer advertsLeft;

    @OneToOne(cascade = CascadeType.MERGE, targetEntity = EmlakUser.class)
    @JoinColumn(name = "emlak_user_id", referencedColumnName = "id")
    @JsonIgnore
    private EmlakUser emlakUser;
}
