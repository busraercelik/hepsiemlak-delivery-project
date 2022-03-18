package com.bsr.emlak.burada.service;

import com.bsr.emlak.commons.dto.request.PropertyRequestDTO;
import com.bsr.emlak.commons.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public void saveProperty(PropertyRequestDTO propertyRequestDTO) {
        //propertyRepository.save()
    }
}
