package com.bsr.emlak.commons.factory;

import com.bsr.emlak.commons.entity.Commercial;
import com.bsr.emlak.commons.entity.Land;
import com.bsr.emlak.commons.entity.Property;
import com.bsr.emlak.commons.entity.Residential;
import com.bsr.emlak.commons.enums.PropertyType;

public class PropertyFactory {

    public static Property getProperty(PropertyType propertyType) {
        if (propertyType == null) {
            return null;
        }

        if (propertyType == PropertyType.LAND) {
            return new Land();
        }

        if (propertyType == PropertyType.COMMERCIAL) {
            return new Commercial();
        }

        if (propertyType == PropertyType.RESIDENTIAL) {
            return new Residential();
        }

        return null;
    }
}
