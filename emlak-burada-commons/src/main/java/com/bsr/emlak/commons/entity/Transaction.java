package com.bsr.emlak.commons.entity;

import com.bsr.emlak.commons.enums.PaymentMethod;
import com.bsr.emlak.commons.enums.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "transaction")
public class Transaction extends BaseEntity{
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, targetEntity = Product.class)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private String accountNumber;

    @Column(name = "completed_at")
    protected LocalDateTime completedAt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, targetEntity = EmlakUser.class)
    @JoinColumn(name = "emlak_user_id", referencedColumnName = "id")
    private EmlakUser emlakUser;
}
