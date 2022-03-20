package com.bsr.emlak.commons.dto.request;

import lombok.Data;

@Data
public class InitiateTransactionRequestDTO {
    private String accountNumber;
    private Long productId;
    private Long emlakUserId;
}
