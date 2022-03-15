package com.bsr.emlak.commons.dto.request;

import com.bsr.emlak.commons.enums.PersonType;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PersonRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private PersonType userType;
}
