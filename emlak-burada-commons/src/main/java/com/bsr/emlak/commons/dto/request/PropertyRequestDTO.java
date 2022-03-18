package com.bsr.emlak.commons.dto.request;

import com.bsr.emlak.commons.enums.PropertyType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class PropertyRequestDTO {
    private String description;
    private Long addressId;
    private Float grossSquareMeter;
    private Boolean isForSale;
    private PropertyType propertyType;
    private CommercialPropertyDetails commercialPropertyDetails;
    private LandPropertyDetails landPropertyDetails;
    private ResidentialPropertyDetails residentialPropertyDetails;

    public static class CommercialPropertyDetails {
        private String category;
        private Integer dues;
    }

    public static class LandPropertyDetails {
        private Integer parcelNo;
        private String zoning;
    }

    public static class ResidentialPropertyDetails {
        private String noOfRoom;
        private Integer noOfBath;
        private Integer floor;
        private Integer buildingAge;
        private Boolean isEligibleForCredit;
    }
}
