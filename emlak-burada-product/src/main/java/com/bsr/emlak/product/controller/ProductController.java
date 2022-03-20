package com.bsr.emlak.product.controller;

import com.bsr.emlak.commons.dto.request.InitiateTransactionRequestDTO;
import com.bsr.emlak.commons.dto.response.jsonview.TransactionView;
import com.bsr.emlak.product.service.ProductService;
import com.bsr.emlak.product.service.TransactionService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private final ProductService productService;
    private final TransactionService purchaseService;

    @Autowired
    public ProductController(ProductService productService, TransactionService transactionService) {
        this.productService = productService;
        this.purchaseService = transactionService;
    }

    @GetMapping(value = "/products")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping(value = "/transaction")
    @JsonView(TransactionView.PENDING.class)
    public ResponseEntity<?> initiateTransaction(@RequestBody InitiateTransactionRequestDTO purchaseRequestDTO) {
        return ResponseEntity.ok(purchaseService.initiateTransaction(purchaseRequestDTO));
    }
}
