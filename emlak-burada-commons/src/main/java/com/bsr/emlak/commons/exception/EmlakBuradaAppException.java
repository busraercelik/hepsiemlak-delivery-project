package com.bsr.emlak.commons.exception;

import com.bsr.emlak.commons.constant.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class EmlakBuradaAppException extends RuntimeException {

    private ErrorCode errorCode;
    private Integer httpStatusCode;

    public EmlakBuradaAppException(ErrorCode code, Integer httpStatusCode) {
        super(code.getMessage());
        this.errorCode = code;
        this.httpStatusCode = httpStatusCode;
    }
}
