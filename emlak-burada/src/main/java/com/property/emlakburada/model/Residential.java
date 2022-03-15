package com.property.emlakburada.model;;

import com.property.emlakburada.dto.ResidentialType;
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
@Table(name = "residential")
public class Residential extends Property {

    private String noOfRoom;

    private int noOfBath;

    private int floor;

    private int buildingAge;

    private boolean isEligibleForCredit;

    @Override
    public List<ResidentialType> getTypes() {
        return Arrays.asList(ResidentialType.values());
    }
}
