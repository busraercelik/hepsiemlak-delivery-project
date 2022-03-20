package com.bsr.emlak.commons.dto.request;

import com.bsr.emlak.commons.enums.TransactionStatus;
import lombok.Data;

@Data
public class UpdateTransactionRequestDTO {
    private Long transactionId;
    private TransactionStatus transactionStatus;
}
