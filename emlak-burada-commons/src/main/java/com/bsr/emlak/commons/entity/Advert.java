package com.bsr.emlak.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "advert")
public class Advert {
    @Id
    @Column(insertable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int adNo;

    private String description;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Property property;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person postedBy;

    @Transient
    private String[] imageList = new String[5];
    private BigDecimal cost;
    private int duration;
    private boolean shouldHighlighted = false;
    private boolean isReviewed = false;
    private LocalDate postedDate;
    private boolean isActive;


    public Advert(Property realEstate, Person user, String[] imageList) {
        this.property = realEstate;
        this.user = user;
        this.imageList = imageList;
    }
}
