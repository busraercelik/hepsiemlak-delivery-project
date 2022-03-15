package com.property.emlakburada.dto.response;

import com.property.emlakburada.model.Advert;
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
