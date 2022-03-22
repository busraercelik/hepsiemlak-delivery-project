package com.bsr.emlak.product.controller;

import com.bsr.emlak.commons.dto.request.StartTransactionRequestDTO;
import com.bsr.emlak.commons.dto.request.UpdateTransactionRequestDTO;
import com.bsr.emlak.commons.dto.response.jsonview.TransactionView;
import com.bsr.emlak.product.service.TransactionService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class TransactionController {

    private final TransactionService purchaseService;

    @Autowired
    public TransactionController(TransactionService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping(value = "/start_transaction")
    @JsonView(TransactionView.PENDING.class)
    public ResponseEntity<?> startTransaction(
            @RequestBody StartTransactionRequestDTO transactionRequestDTO,
            @RequestHeader(value="emlak_user_id") String emlakUserId) {
        log.info("startTransaction --> User id : {}", emlakUserId);
        transactionRequestDTO.setEmlakUserId(Long.valueOf(emlakUserId));
        return ResponseEntity.ok(purchaseService.initiateTransaction(transactionRequestDTO));
    }

    @PutMapping(value = "/complete_transaction")
    @JsonView(TransactionView.COMPLETED.class)
    public ResponseEntity<?> completeTransaction(
            @RequestBody UpdateTransactionRequestDTO updateTransactionRequestDTO) {
        return ResponseEntity.ok(purchaseService.completeTransaction(updateTransactionRequestDTO));
    }
}
