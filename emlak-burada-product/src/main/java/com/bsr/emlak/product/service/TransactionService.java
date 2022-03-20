package com.bsr.emlak.product.service;

import com.bsr.emlak.commons.dto.request.InitiateTransactionRequestDTO;
import com.bsr.emlak.commons.dto.response.TransactionResponseDTO;
import com.bsr.emlak.commons.entity.EmlakUser;
import com.bsr.emlak.commons.entity.Product;
import com.bsr.emlak.commons.entity.Transaction;
import com.bsr.emlak.commons.enums.PaymentMethod;
import com.bsr.emlak.commons.enums.TransactionStatus;
import com.bsr.emlak.commons.repository.TransactionRepository;
import com.bsr.emlak.commons.service.EmlakUserService;
import com.bsr.emlak.commons.transformers.TransactionTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {
    private final ProductService productService;
    private final TransactionRepository transactionRepository;
    private final EmlakUserService emlakUserService;

    @Autowired
    public TransactionService(ProductService productService, TransactionRepository transactionRepository, EmlakUserService emlakUserService) {
        this.productService = productService;
        this.transactionRepository = transactionRepository;
        this.emlakUserService = emlakUserService;
    }

    public TransactionResponseDTO initiateTransaction(InitiateTransactionRequestDTO purchaseRequestDTO) {

        Optional<EmlakUser> optionalEmlakUser = Optional.ofNullable(emlakUserService
                .getEmlakUserById(purchaseRequestDTO.getEmlakUserId()));

        if (optionalEmlakUser.isEmpty()) {
            throw new RuntimeException(String.format("User with id: %d is not found",
                    purchaseRequestDTO.getEmlakUserId()));
        }

        Optional<Product> optionalProduct = Optional.ofNullable(productService
                .getProductById(purchaseRequestDTO.getProductId()));

        if (optionalProduct.isEmpty()) {
            throw new RuntimeException(String.format("Product with id: %d is not found",
                    purchaseRequestDTO.getProductId()));
        }

        EmlakUser emlakUser = optionalEmlakUser.get();
        Product product = optionalProduct.get();

        Transaction transaction = Transaction.builder()
                .amount(product.getCost())
                .transactionStatus(TransactionStatus.PENDING)
                .product(product)
                .paymentMethod(PaymentMethod.BANK_ACCOUNT)
                .accountNumber(purchaseRequestDTO.getAccountNumber())
                .completedAt(null)
                .emlakUser(emlakUser)
                .build();

        TransactionResponseDTO responseDTO = TransactionTransformer
                .Response.transform(transactionRepository.save(transaction));

        return responseDTO;
    }
}
