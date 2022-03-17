package com.bsr.emlak.commons.dto.request;

import com.bsr.emlak.commons.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EmlakUserRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNo;
    private UserType userType;
}
