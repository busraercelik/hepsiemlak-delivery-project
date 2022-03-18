package com.bsr.emlak.commons.entity.property;

import com.bsr.emlak.commons.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "commerical")
public class Commercial extends Property {
    private String category;
    private Integer dues;

    @Override
    public PropertyType getPropertyType() {
        return PropertyType.COMMERCIAL;
    }
}
