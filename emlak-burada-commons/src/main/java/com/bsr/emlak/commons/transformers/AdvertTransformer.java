package com.bsr.emlak.commons.transformers;

import com.bsr.emlak.commons.dto.request.AdvertRequestDTO;
import com.bsr.emlak.commons.entity.Advert;
import com.bsr.emlak.commons.entity.EmlakUser;
import com.bsr.emlak.commons.entity.property.Property;
import com.bsr.emlak.commons.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdvertTransformer {

    private final PropertyRepository propertyRepository;

    @Autowired
    public AdvertTransformer(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public Advert transform(AdvertRequestDTO request) {
        Property property = propertyRepository.findById(request.getPropertyId()).orElse(null);
        Advert advert = new Advert(property, new EmlakUser());
        advert.setTitle(request.getTitle());
        advert.setCost(request.getCost());

        return advert;
    }
}
