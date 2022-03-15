package com.bsr.emlak.commons.entity;

import com.bsr.emlak.commons.enums.CommercialType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "commerical")
public class Commercial extends Property{
    private String category;
    private int dues;
    @Override
    public List<CommercialType> getTypes() {
        return Arrays.asList(CommercialType.values());
    }
}
