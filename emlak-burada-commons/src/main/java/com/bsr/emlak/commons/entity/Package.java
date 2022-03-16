package com.bsr.emlak.commons.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "package")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Advert> adverts;
    private int validityPeriodDays;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endOfValidityPeriod;
}
