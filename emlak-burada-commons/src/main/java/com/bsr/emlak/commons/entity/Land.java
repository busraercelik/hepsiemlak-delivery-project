package com.bsr.emlak.commons.entity;

import com.bsr.emlak.commons.enums.LandType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Land extends Property {

    private int parcelNo;

    private String zoning;

    @Override
    public List<LandType> getTypes() {
        return Arrays.asList(LandType.values());
    }
}
