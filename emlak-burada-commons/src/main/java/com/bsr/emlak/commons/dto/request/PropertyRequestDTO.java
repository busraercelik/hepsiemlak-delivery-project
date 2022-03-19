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
    private Long emlakUserId;
    private Float grossSquareMeter;
    private Boolean isForSale;
    private PropertyType propertyType;
    private CommercialPropertyDetails commercialPropertyDetails;
    private LandPropertyDetails landPropertyDetails;
    private ResidentialPropertyDetails residentialPropertyDetails;

    @Getter
    public static class CommercialPropertyDetails {
        private String category;
        private Integer dues;
    }

    @Getter
    public static class LandPropertyDetails {
        private Integer parcelNo;
        private String zoning;
    }

    @Getter
    public static class ResidentialPropertyDetails {
        private Integer noOfRoom;
        private Integer noOfBath;
        private Integer floor;
        private Integer buildingAge;
        private Boolean isEligibleForCredit;
    }
}
