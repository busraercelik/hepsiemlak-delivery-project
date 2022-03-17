package com.bsr.emlak.commons.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "subscription")
public class Subscription extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    private LocalDateTime datePurchased;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private EmlakUser emlakUser;

    private BigDecimal purchaseCost;

    @PrePersist
    @PreUpdate
    public void updateInternalFields() {
        this.createdAt = ObjectUtils.isEmpty(this.createdAt) ? LocalDateTime.now() : this.createdAt;
        this.modifiedAt = LocalDateTime.now();
    }
}
