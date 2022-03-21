package com.bsr.emlak.commons.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "emlak_user_authentication")
public class EmlakUserAuthentication extends BaseEntity {
    @OneToOne(targetEntity = EmlakUser.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "emlak_user_id", referencedColumnName = "id")
    private EmlakUser emlakUser;
    private String passwordHash;
}
