package com.property.emlakburada.model;;

import com.property.emlakburada.model.enums.PersonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNo;
    @Enumerated(EnumType.STRING)
    private PersonType personType;

    @OneToMany(targetEntity = Advert.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "pa_fk", referencedColumnName = "id")
    private List<Advert> favouriteAdverts;

    @OneToMany(targetEntity = Advert.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "pa_fk", referencedColumnName = "id")
    private List<Advert> publishedAdverts;

    public Person(String firstName, String lastName, String email, PersonType personType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.personType = personType;
    }
}
