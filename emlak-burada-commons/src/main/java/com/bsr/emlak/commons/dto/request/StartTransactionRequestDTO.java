package com.bsr.emlak.commons.dto.request;

import lombok.Data;

@Data
public class StartTransactionRequestDTO extends BaseRequestDTO{
    private String accountNumber;
    private Long productId;
}
