package com.bsr.emlak.commons.entity;

import lombok.*;
import org.apache.commons.lang3.ObjectUtils;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product")
public class Product extends BaseEntity {
    private String productName;
    private Integer totalAdverts;
    private Integer validityPeriodDays;
    private BigDecimal cost;
}
