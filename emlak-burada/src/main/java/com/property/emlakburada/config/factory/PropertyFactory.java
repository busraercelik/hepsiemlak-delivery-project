package com.property.emlakburada.config.factory;


import com.property.emlakburada.dto.PropertyType;
import com.property.emlakburada.model.Commercial;
import com.property.emlakburada.model.Land;
import com.property.emlakburada.model.Property;
import com.property.emlakburada.model.Residential;

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
