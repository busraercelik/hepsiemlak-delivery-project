package com.property.emlakburada.model;;

import com.property.emlakburada.dto.CommercialType;
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
