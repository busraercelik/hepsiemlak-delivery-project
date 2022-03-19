package com.bsr.emlak.commons.transformers;

import com.bsr.emlak.commons.dto.request.PropertyRequestDTO;
import com.bsr.emlak.commons.entity.EmlakUser;
import com.bsr.emlak.commons.entity.property.Property;
import com.bsr.emlak.commons.factory.PropertyFactory;
import com.bsr.emlak.commons.repository.EmlakUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PropertyTransformer {

    private final PropertyFactory propertyFactory;
    private final EmlakUserRepository emlakUserRepository;

    @Autowired
    public PropertyTransformer(PropertyFactory propertyFactory, EmlakUserRepository emlakUserRepository) {
        this.propertyFactory = propertyFactory;
        this.emlakUserRepository = emlakUserRepository;
    }

    public Property transform(PropertyRequestDTO propertyRequestDTO) {
        Property property = propertyFactory.getProperty(propertyRequestDTO);
        Optional<EmlakUser> owner = emlakUserRepository.findById(propertyRequestDTO.getEmlakUserId());
        owner.orElseThrow(()-> new RuntimeException(String.format("User with id %s is not found!",
                propertyRequestDTO.getEmlakUserId())));
        owner.ifPresent(nonEmptyEmlakUser-> {
            property.setOwner(nonEmptyEmlakUser);
            property.setCreatedBy(nonEmptyEmlakUser.getId());
            property.setModifiedBy(nonEmptyEmlakUser.getId());
        });
        return property;
    }
}
