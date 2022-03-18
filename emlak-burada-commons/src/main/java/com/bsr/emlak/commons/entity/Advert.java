package com.bsr.emlak.commons.entity;

import com.bsr.emlak.commons.entity.property.Property;
import com.bsr.emlak.commons.enums.AdvertStatus;
import lombok.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "advert")
public class Advert extends BaseEntity{

    private String advertUUID;
    private String title;
    private String description;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Property property;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "favouriteAdverts")
    private Set<EmlakUser> favouriteOf;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posted_by_id", referencedColumnName = "id")
    private EmlakUser postedBy;

    @ElementCollection
    private List<String> imageList;
    private BigDecimal cost;
    private Integer duration;

    private Boolean shouldHighlighted = false;
    private Boolean isReviewed = false;
    private Boolean isActive;

    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private AdvertStatus advertStatus;

    public Advert(Property realEstate, EmlakUser emlakUser) {
        this.property = realEstate;
        this.postedBy = emlakUser;
    }

    @PrePersist
    @PreUpdate
    public void updateInternalFields() {
        this.createdAt = ObjectUtils.isEmpty(this.createdAt) ? LocalDateTime.now() : this.createdAt;
        this.advertUUID = StringUtils.isEmpty(this.advertUUID) ? UUID.randomUUID().toString() : this.advertUUID;
        this.modifiedAt = LocalDateTime.now();
    }
}
