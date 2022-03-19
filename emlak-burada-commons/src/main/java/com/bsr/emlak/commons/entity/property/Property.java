package com.bsr.emlak.commons.entity.property;

import com.bsr.emlak.commons.entity.Address;
import com.bsr.emlak.commons.entity.BaseEntity;
import com.bsr.emlak.commons.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Property extends BaseEntity {

    private String description;
    private Float grossSquareMeter;
    private Boolean isForSale;

    public abstract PropertyType getPropertyType();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}
