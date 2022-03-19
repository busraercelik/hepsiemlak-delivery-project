package com.bsr.emlak.commons.factory;

import com.bsr.emlak.commons.dto.request.PropertyRequestDTO;
import com.bsr.emlak.commons.entity.property.Commercial;
import com.bsr.emlak.commons.entity.property.Land;
import com.bsr.emlak.commons.entity.property.Property;
import com.bsr.emlak.commons.entity.property.Residential;
import com.bsr.emlak.commons.enums.PropertyType;
import com.bsr.emlak.commons.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropertyFactory {

    private final AddressRepository addressRepository;

    @Autowired
    public PropertyFactory(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Property getProperty(PropertyRequestDTO requestDTO) {
        PropertyType propertyType = requestDTO.getPropertyType();

        switch (propertyType){
            case LAND:
                Land land = new Land();
                updateCommonPropertyDetails(requestDTO, land);

                land.setZoning(requestDTO.getLandPropertyDetails().getZoning());
                land.setParcelNo(requestDTO.getLandPropertyDetails().getParcelNo());

                return land;

            case COMMERCIAL:
                Commercial commercial = new Commercial();
                updateCommonPropertyDetails(requestDTO, commercial);

                commercial.setCategory(requestDTO.getCommercialPropertyDetails().getCategory());
                commercial.setDues(requestDTO.getCommercialPropertyDetails().getDues());

                return commercial;

            case RESIDENTIAL:
                Residential residential =  new Residential();
                updateCommonPropertyDetails(requestDTO, residential);

                residential.setBuildingAge(requestDTO.getResidentialPropertyDetails().getBuildingAge());
                residential.setFloor(requestDTO.getResidentialPropertyDetails().getFloor());
                residential.setIsEligibleForCredit(requestDTO.getResidentialPropertyDetails().getIsEligibleForCredit());
                residential.setNoOfBath(requestDTO.getResidentialPropertyDetails().getNoOfBath());
                residential.setNoOfRoom(requestDTO.getResidentialPropertyDetails().getNoOfRoom());

                return residential;
        }
        throw new RuntimeException("Failed to create a property from request!");
    }

    private void updateCommonPropertyDetails(PropertyRequestDTO requestDTO, Property property) {
        /* common property attrıbutes */
        property.setIsForSale(requestDTO.getIsForSale());
        property.setGrossSquareMeter(requestDTO.getGrossSquareMeter());
        property.setDescription(requestDTO.getDescription());
        property.setAddress(addressRepository.getById(requestDTO.getAddressId()));
    }
}
