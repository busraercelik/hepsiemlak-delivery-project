package com.bsr.emlak.commons.dto.request;

import com.bsr.emlak.commons.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class EmlakUserRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNo;
    private UserType userType;
}
