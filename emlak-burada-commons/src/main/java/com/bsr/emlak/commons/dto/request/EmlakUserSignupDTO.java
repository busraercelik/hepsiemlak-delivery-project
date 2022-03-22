package com.bsr.emlak.commons.dto.request;

import com.bsr.emlak.commons.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class EmlakUserSignupDTO {
    private String firstName;
    private String lastName;
    private String passwordHash;
    private String email;
    private String mobileNo;
    private UserType userType;
}
