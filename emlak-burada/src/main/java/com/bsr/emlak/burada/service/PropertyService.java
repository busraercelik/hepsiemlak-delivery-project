package com.bsr.emlak.burada.service;


import com.bsr.emlak.commons.dto.request.PropertyRequestDTO;
import com.bsr.emlak.commons.entity.property.Property;
import com.bsr.emlak.commons.factory.PropertyFactory;
import com.bsr.emlak.commons.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyFactory propertyFactory;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository, PropertyFactory propertyFactory) {
        this.propertyRepository = propertyRepository;
        this.propertyFactory = propertyFactory;
    }

    public Property saveProperty(PropertyRequestDTO propertyRequestDTO) {
        return propertyRepository.save(propertyFactory.getProperty(propertyRequestDTO));
    }

    public Property getById(Long id) {
        return propertyRepository.findById(id).orElse(null);
    }
}
