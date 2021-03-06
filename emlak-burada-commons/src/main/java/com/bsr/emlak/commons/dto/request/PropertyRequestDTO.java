package com.bsr.emlak.commons.dto.request;

import com.bsr.emlak.commons.enums.PropertyType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class PropertyRequestDTO extends BaseRequestDTO{
    private String description;
    private Long addressId;
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
