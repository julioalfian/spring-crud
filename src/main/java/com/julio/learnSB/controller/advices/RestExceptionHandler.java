package com.julio.learnSB.controller.advices;

import com.julio.learnSB.response.BaseResponse;
import com.julio.learnSB.util.ResponseHelper;
import com.julio.learnSB.util.exceptions.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Set;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<BaseResponse> handleBadRequest(BadRequestException ex) {
        ex.printStackTrace();
        log.warn("handleBadRequest: {}", ex.getMessage());
        return ResponseHelper.buildBadRequestResponse(ex.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<BaseResponse> handleNullPointerException(Exception ex) {
        ex.printStackTrace();
        log.warn("handleNullPointerException: {}", ex.getMessage());
         return ResponseHelper.buildBadRequestResponse("Terdapat parameter yang kosong");
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<BaseResponse> handleNoSuchElementException(Exception ex) {
        ex.printStackTrace();
         log.warn("handleNoSuchElementException: {}", ex.getMessage());
        return ResponseHelper.buildBadRequestResponse(ex.getMessage());
    }

    @ExceptionHandler(TransactionSystemException.class)
    protected ResponseEntity<BaseResponse> handleTransactionSystemException(TransactionSystemException ex) {

        ex.printStackTrace();
        log.warn("handleTransactionSystemException: {}", ex.getMessage());

        Throwable cause = ex.getRootCause();

        StringBuilder response = new StringBuilder();
        if (cause instanceof ConstraintViolationException) {

            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();
            constraintViolations.forEach(constraintViolation -> {
                response.append(constraintViolation.getPropertyPath().toString() + " : " + constraintViolation.getMessage() + " ; ");
            });
            return ResponseHelper.buildBadRequestResponse(response.toString());
        }
        return ResponseHelper.buildBadRequestResponse("Error Transaction System");
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<BaseResponse> handleExceptionSystemException(Exception ex) {
        ex.printStackTrace();
         log.warn("handleTransactionSystemException: {}", ex.getMessage());
        return ResponseHelper.buildInternalServerErrorResponse("Terdapat kesalahan pada sistem");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<BaseResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ex.printStackTrace();
         log.warn("handleIllegalArgumentException: {}", ex.getMessage());
        return ResponseHelper.buildBadRequestResponse(ex.getMessage());

    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ex.printStackTrace();
        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        HashMap<String, ArrayList<String>> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(err -> {
                    errors.put(err.getField(), new ArrayList());
                });

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(err -> {
                    errors.get(err.getField()).add(err.getDefaultMessage());
                });


        BaseResponse responseBody = BaseResponse.builder()
                .success(false)
                .data(errors)
                .build();

        return handleExceptionInternal(
                ex, responseBody, headers, HttpStatus.BAD_REQUEST, request);

    }

}
