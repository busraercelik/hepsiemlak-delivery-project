package com.bsr.emlak.burada.service;


import com.bsr.emlak.commons.dto.request.PropertyRequestDTO;
import com.bsr.emlak.commons.entity.property.Property;
import com.bsr.emlak.commons.repository.PropertyRepository;
import com.bsr.emlak.commons.transformers.PropertyTransformer;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyTransformer propertyTransformer;

    public PropertyService(PropertyRepository propertyRepository, PropertyTransformer propertyTransformer) {
        this.propertyRepository = propertyRepository;
        this.propertyTransformer = propertyTransformer;
    }

    public Property saveProperty(PropertyRequestDTO propertyRequestDTO) {
        return propertyRepository.save(propertyTransformer.transform(propertyRequestDTO));
    }

    public Property getById(Long id) {
        return propertyRepository.findById(id).orElse(null);
    }
}
