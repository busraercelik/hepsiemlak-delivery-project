package com.property.emlakburada.dto;

import com.property.emlakburada.model.enums.PersonType;
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
