package com.bsr.emlak.burada.config.factory;


import com.bsr.emlak.commons.enums.PropertyType;
import com.bsr.emlak.commons.entity.property.Commercial;
import com.bsr.emlak.commons.entity.property.Land;
import com.bsr.emlak.commons.entity.property.Property;
import com.bsr.emlak.commons.entity.property.Residential;


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
