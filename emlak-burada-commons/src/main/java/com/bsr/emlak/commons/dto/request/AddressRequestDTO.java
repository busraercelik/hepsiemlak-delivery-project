package com.bsr.emlak.commons.dto.request;

import lombok.Data;

@Data
public class AddressRequestDTO extends BaseRequestDTO{
    private String city;
    private String district;
    private String addressDesc;
}
