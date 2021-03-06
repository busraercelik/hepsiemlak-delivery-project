package com.bsr.emlak.product.service;

import com.bsr.emlak.commons.dto.request.StartTransactionRequestDTO;
import com.bsr.emlak.commons.dto.request.UpdateTransactionRequestDTO;
import com.bsr.emlak.commons.dto.response.TransactionResponseDTO;
import com.bsr.emlak.commons.entity.*;
import com.bsr.emlak.commons.enums.PaymentMethod;
import com.bsr.emlak.commons.enums.TransactionStatus;
import com.bsr.emlak.commons.exception.EmlakBuradaAppException;
import com.bsr.emlak.commons.repository.EmlakUserRepository;
import com.bsr.emlak.commons.repository.TransactionRepository;
import com.bsr.emlak.commons.transformers.TransactionTransformer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.bsr.emlak.commons.constant.ErrorCode.*;

@Slf4j
@Service
public class TransactionService {
    private final ProductService productService;
    private final TransactionRepository transactionRepository;
    private final EmlakUserRepository emlakUserRepository;

    @Autowired
    public TransactionService(
            ProductService productService, TransactionRepository transactionRepository,
            EmlakUserRepository emlakUserRepository) {
        this.productService = productService;
        this.transactionRepository = transactionRepository;
        this.emlakUserRepository = emlakUserRepository;
    }

    public TransactionResponseDTO initiateTransaction(StartTransactionRequestDTO purchaseRequestDTO) {

        Optional<EmlakUser> optionalEmlakUser = emlakUserRepository.findById(purchaseRequestDTO.getEmlakUserId());

        if (optionalEmlakUser.isEmpty()) {
            throw EmlakBuradaAppException.builder()
                    .errorCode(USER_NOT_FOUND.formatted(purchaseRequestDTO.getEmlakUserId()))
                    .httpStatusCode(400)
                    .build();
        }

        Optional<Product> optionalProduct = Optional.ofNullable(productService
                .getProductById(purchaseRequestDTO.getProductId()));

        if (optionalProduct.isEmpty()) {
            throw EmlakBuradaAppException.builder()
                    .errorCode(PACKAGE_NOT_FOUND.formatted(purchaseRequestDTO.getProductId()))
                    .httpStatusCode(400)
                    .build();
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
        transaction.setCreatedBy(emlakUser.getId());

        TransactionResponseDTO responseDTO = TransactionTransformer
                .Response.transform(transactionRepository.save(transaction));

        return responseDTO;
    }

    /** Run the following operations when a transaction is completed :
     *  1) update transaction status to complete
     *  2) update completedAt field in transaction
     *  3) add a record in purchase product history
     *  4) update usage_left table
     **/
    @Transactional
    public TransactionResponseDTO completeTransaction(UpdateTransactionRequestDTO updateTransactionRequestDTO) {
        /* transaction must be completed if this method is called */
        if (updateTransactionRequestDTO.getTransactionStatus()!=TransactionStatus.COMPLETE) {
            throw EmlakBuradaAppException.builder()
                    .errorCode(INVALID_TRANSACTION_STATUS
                            .formatted(updateTransactionRequestDTO.getTransactionStatus()))
                    .httpStatusCode(400)
                    .build();
        }

        /* load the transaction from db  */
        Transaction transaction = transactionRepository
                .findById(updateTransactionRequestDTO.getTransactionId())
                .orElseThrow(()-> EmlakBuradaAppException.builder()
                        .errorCode(TRANSACTION_NOT_FOUND.formatted(updateTransactionRequestDTO.getTransactionId()))
                        .httpStatusCode(400)
                        .build());

        /* update the transaction status and complete date */
        transaction.setTransactionStatus(TransactionStatus.COMPLETE);
        transaction.setCompletedAt(LocalDateTime.now());

        /* load the user associated with the transaction */
        EmlakUser emlakUser = transaction.getEmlakUser();
        transaction.setModifiedBy(emlakUser.getId());

        /* set product purchase history */
        ProductPurchaseHistory newPurchase = new ProductPurchaseHistory();
        newPurchase.setEmlakUser(emlakUser);
        newPurchase.setProduct(transaction.getProduct());
        newPurchase.setDatePurchased(transaction.getCompletedAt());
        newPurchase.setPurchaseCost(transaction.getAmount());
        newPurchase.setCreatedBy(emlakUser.getId());
        newPurchase.setModifiedBy(emlakUser.getId());

        /* update user's product purchase history */
        emlakUser.getProductPurchaseHistories().add(newPurchase);

        /* update usage left for emlakuser  */
        UsageLeft usageLeft = emlakUser.getUsageLeft();

        if(ObjectUtils.isEmpty(usageLeft)) {
            usageLeft = new UsageLeft();
            emlakUser.setUsageLeft(usageLeft);
        }
        usageLeft.setCreatedBy(emlakUser.getId());
        usageLeft.setModifiedBy(emlakUser.getId());


        /* load existing total adverts left */
        Integer advertsLeft = ObjectUtils.isEmpty(
                usageLeft.getAdvertsLeft()) ? 0 : usageLeft.getAdvertsLeft();

        /* update the number of advert posts left */
        usageLeft.setAdvertsLeft(advertsLeft + transaction.getProduct().getTotalAdverts());

        /* load total number of days for the product */
        Integer validityPeriodDays = transaction.getProduct().getValidityPeriodDays();

        /* calculate and set the last date */
        usageLeft.setLastDate(transaction.getCompletedAt().plusDays(validityPeriodDays));

        usageLeft.setEmlakUser(transaction.getEmlakUser());
        transaction.getEmlakUser().setUsageLeft(usageLeft);

        /* save the updates on user */
        emlakUserRepository.save(emlakUser);
        /* save the updates on transaction */
        transactionRepository.save(transaction);

        return TransactionTransformer.Response.transform(transaction);
    }
}
