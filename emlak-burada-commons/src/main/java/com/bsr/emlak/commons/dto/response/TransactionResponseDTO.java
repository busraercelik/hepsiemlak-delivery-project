package com.bsr.emlak.commons.dto.response;

import com.bsr.emlak.commons.dto.response.jsonview.TransactionView;
import com.bsr.emlak.commons.enums.PaymentMethod;
import com.bsr.emlak.commons.enums.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDTO {

    @JsonView(TransactionView.PENDING.class)
    private Long transactionId;

    @JsonView(TransactionView.PENDING.class)
    private BigDecimal amount;

    @JsonView(TransactionView.PENDING.class)
    private TransactionStatus transactionStatus;

    @JsonView(TransactionView.PENDING.class)
    private Long productId;

    @JsonView(TransactionView.PENDING.class)
    private PaymentMethod paymentMethod;

    @JsonView(TransactionView.PENDING.class)
    private String accountNumber;

    @JsonView(TransactionView.COMPLETED.class)
    protected LocalDateTime completedAt;

    @JsonView(TransactionView.PENDING.class)
    private Long emlakUserId;
}
