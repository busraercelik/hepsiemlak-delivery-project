package com.bsr.emlak.commons.entity.property;

import com.bsr.emlak.commons.enums.PropertyType;
import lombok.*;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Land extends Property {
    private Integer parcelNo;
    private String zoning;

    @Override
    public PropertyType getPropertyType() {
        return PropertyType.LAND;
    }
}
