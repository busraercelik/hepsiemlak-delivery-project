package com.bsr.emlak.commons.transformers;

import com.bsr.emlak.commons.dto.request.PropertyRequestDTO;
import com.bsr.emlak.commons.entity.Address;
import com.bsr.emlak.commons.entity.EmlakUser;
import com.bsr.emlak.commons.entity.property.Property;
import com.bsr.emlak.commons.exception.EmlakBuradaAppException;
import com.bsr.emlak.commons.factory.PropertyFactory;
import com.bsr.emlak.commons.repository.AddressRepository;
import com.bsr.emlak.commons.repository.EmlakUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.bsr.emlak.commons.constant.ErrorCode.ADDRESS_NOT_FOUND;
import static com.bsr.emlak.commons.constant.ErrorCode.USER_NOT_FOUND;

@Component
public class PropertyTransformer {

    private final PropertyFactory propertyFactory;
    private final EmlakUserRepository emlakUserRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public PropertyTransformer(PropertyFactory propertyFactory, EmlakUserRepository emlakUserRepository, AddressRepository addressRepository) {
        this.propertyFactory = propertyFactory;
        this.emlakUserRepository = emlakUserRepository;
        this.addressRepository = addressRepository;
    }

    public Property transform(PropertyRequestDTO propertyRequestDTO) {
        Property property = propertyFactory.getProperty(propertyRequestDTO);
        Optional<EmlakUser> owner = emlakUserRepository.findById(propertyRequestDTO.getEmlakUserId());
        owner.orElseThrow(()->
            EmlakBuradaAppException.builder()
                    .errorCode(USER_NOT_FOUND.formatted(propertyRequestDTO.getEmlakUserId()))
                    .httpStatusCode(400)
                    .build()
        );
        Address address = addressRepository.findById(propertyRequestDTO.getAddressId())
                .orElseThrow(()->
                        EmlakBuradaAppException.builder()
                                .errorCode(ADDRESS_NOT_FOUND.formatted(propertyRequestDTO.getAddressId()))
                                .httpStatusCode(400)
                                .build());

        owner.ifPresent(nonEmptyEmlakUser-> {
            property.setOwner(nonEmptyEmlakUser);
            property.setCreatedBy(nonEmptyEmlakUser.getId());
            property.setModifiedBy(nonEmptyEmlakUser.getId());
            property.setAddress(address);
        });
        return property;
    }
}
