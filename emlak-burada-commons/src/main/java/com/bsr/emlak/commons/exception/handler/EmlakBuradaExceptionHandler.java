package com.bsr.emlak.commons.exception.handler;

import com.bsr.emlak.commons.exception.EmlakBuradaAppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;

@Slf4j
@ControllerAdvice
public class EmlakBuradaExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { EmlakBuradaAppException.class })
    protected ResponseEntity<Object> handleConflict(EmlakBuradaAppException ex, WebRequest request) {

        HashMap<String, String> errorMap = new HashMap<>();
        errorMap.put("message", ex.getErrorCode().getMessage());
        errorMap.put("code", ex.getErrorCode().getCode().toString());
        errorMap.put("timestamp", LocalDateTime.now().toString());

        return handleExceptionInternal(ex, errorMap,
                new HttpHeaders(), HttpStatus.valueOf(ex.getHttpStatusCode()), request);
    }
}
