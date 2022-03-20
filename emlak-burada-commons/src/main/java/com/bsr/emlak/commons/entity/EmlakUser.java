package com.bsr.emlak.commons.entity;

import com.bsr.emlak.commons.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "emlak_user")
public class EmlakUser extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNo;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany(targetEntity = SavedSearch.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JoinColumn(name = "emlak_user_id",  referencedColumnName = "id")
    private Set<SavedSearch> savedSearches;

    @ManyToMany(targetEntity = Advert.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "emlak_user_favourite_advert",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "advert_id", referencedColumnName = "id")})
    private Set<Advert> favouriteAdverts;

    @OneToMany(targetEntity = Advert.class,
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY,
            mappedBy = "postedByEmlakUser")
    private Set<Advert> publishedAdverts;

    @OneToMany(targetEntity = Subscription.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "emlakUser")
    private Set<Subscription> subscriptions;

    @OneToMany(targetEntity = Email.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            mappedBy = "toEmlakUser")
    private Set<Email> receivedEmails;

    public String getFullName(){
        return this.firstName+" "+this.lastName;
    }

    public EmlakUser(String firstName, String lastName, String email, UserType userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
    }
}
