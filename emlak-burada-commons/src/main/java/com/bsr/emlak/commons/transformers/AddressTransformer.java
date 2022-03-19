package com.bsr.emlak.commons.transformers;

import com.bsr.emlak.commons.dto.request.AddressRequestDTO;
import com.bsr.emlak.commons.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressTransformer {

    public Address transform(AddressRequestDTO addressRequestDTO) {
        Address address = new Address();
        address.setAddressDesc(addressRequestDTO.getAddressDesc());
        address.setCity(addressRequestDTO.getCity());
        address.setDistrict(addressRequestDTO.getDistrict());
        address.setCreatedBy(addressRequestDTO.getEmlakUserId());
        address.setModifiedBy(addressRequestDTO.getEmlakUserId());
        return address;
    }
}
