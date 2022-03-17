package com.bsr.emlak.commons.entity.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "residential")
public class Residential extends Property {

    private String noOfRoom;

    private Integer noOfBath;

    private Integer floor;

    private Integer buildingAge;

    private Boolean isEligibleForCredit;

}
