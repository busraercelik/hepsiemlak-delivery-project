package com.bsr.emlak.commons.dto.request;

import lombok.Data;

@Data
public class AddressRequestDTO {
    private String city;
    private String district;
    private String addressDesc;
    private Long emlakUserId;
}
