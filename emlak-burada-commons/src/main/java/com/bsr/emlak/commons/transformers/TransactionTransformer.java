package com.bsr.emlak.commons.transformers;


import com.bsr.emlak.commons.dto.response.TransactionResponseDTO;
import com.bsr.emlak.commons.entity.Transaction;

public class TransactionTransformer {

    public static class Response{
        public static TransactionResponseDTO transform(Transaction transaction){
            TransactionResponseDTO transactionResponseDTO = TransactionResponseDTO.builder()
                    .amount(transaction.getAmount())
                    .transactionStatus(transaction.getTransactionStatus())
                    .productId(transaction.getProduct().getId())
                    .paymentMethod(transaction.getPaymentMethod())
                    .accountNumber(transaction.getAccountNumber())
                    .completedAt(transaction.getCompletedAt())
                    .emlakUserId(transaction.getEmlakUser().getId())
                    .build();
            return transactionResponseDTO;
        }
    }
}
