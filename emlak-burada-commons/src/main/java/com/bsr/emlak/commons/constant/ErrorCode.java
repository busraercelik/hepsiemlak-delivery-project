package com.bsr.emlak.commons.constant;

import lombok.Getter;

@Getter
public enum ErrorCode {

    NO_ACTIVE_PACKAGES_FOUND("You do not have any packages", 100),
    PACKAGE_HAS_EXPIRED("No adverts left or package has expired, Please buy a new package.", 99),
    INVALID_PROPERTY_TYPE("Failed to create a property from request!", 98),
    USER_NOT_FOUND("No person with given id %s found", 97),
    PROPERTY_NOT_FOUND("No property with id %s found!", 96),
    ADVERT_NOT_FOUND("No advert found for the given advert uuid", 95),
    TRANSACTION_NOT_FOUND("Transaction with id : %s not found", 94),
    INVALID_TRANSACTION_STATUS("Invalid transaction status : %s", 93),
    PACKAGE_NOT_FOUND("Product with id: %d is not found", 92);


    private String message;
    private Integer code;

    ErrorCode(String message, Integer code) {
        this.message = message;
        this.code = code;
    }
}
