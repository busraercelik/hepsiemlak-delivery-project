package com.bsr.emlak.commons.entity;

import com.bsr.emlak.commons.entity.property.Property;
import com.bsr.emlak.commons.enums.AdvertStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "advert")
public class Advert extends BaseEntity {

    private String advertUUID;
    private String title;
    private String description;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Property property;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "favouriteAdverts", cascade = CascadeType.MERGE)
    private Set<EmlakUser> favouriteOf;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "posted_by_user_id", referencedColumnName = "id")
    @JsonIgnore
    private EmlakUser postedByEmlakUser;

    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Document.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name ="advert_id", referencedColumnName = "id")
    private List<Document> images;

    private BigDecimal cost;
    private Integer duration;

    private Boolean shouldHighlighted = false;
    private Boolean isReviewed = false;
    private Boolean isActive = true;

    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private AdvertStatus advertStatus = AdvertStatus.IN_REVIEW;

    @PrePersist
    public void updateInternalFields() {
        this.advertUUID = StringUtils.isEmpty(this.advertUUID) ? UUID.randomUUID().toString() : this.advertUUID;
    }
}
