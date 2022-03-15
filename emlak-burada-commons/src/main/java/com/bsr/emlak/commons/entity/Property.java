package com.bsr.emlak.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Property {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int cost;

    private float grossSquareMeter;

    private boolean isForSale;

    @ManyToOne(fetch = FetchType.EAGER)
    private Location location;

    public abstract <T> List<T> getTypes();
}
