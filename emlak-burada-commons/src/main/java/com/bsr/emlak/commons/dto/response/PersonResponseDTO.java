package com.bsr.emlak.commons.dto.response;

import com.bsr.emlak.commons.entity.Advert;
import lombok.Data;

import java.util.List;

@Data
public class PersonResponseDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNo;
    private List<Advert> favouriteAdverts;
}
