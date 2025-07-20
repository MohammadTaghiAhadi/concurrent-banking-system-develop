package com.ahadi.concurrent_banking_system.config.exception;

import com.ahadi.concurrent_banking_system.config.core.ApiSubError;
import com.ahadi.concurrent_banking_system.config.core.ApiValidationError;
import com.ahadi.concurrent_banking_system.config.core.BusinessException;
import com.ahadi.concurrent_banking_system.config.core.ResponseModel;
import com.ahadi.concurrent_banking_system.util.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    public Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        ResponseModel responseModel= new ResponseModel(HttpStatus.BAD_REQUEST, error, ex);
        logger.info("Response:"+error);
        return buildResponseEntity(responseModel);
    }

    private ResponseEntity<Object> buildResponseEntity(ResponseModel responseModel) {
        return new ResponseEntity<>(responseModel, responseModel.getStatus());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        ResponseModel apiError = new ResponseModel(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        logger.info("Response:"+apiError.getMessage());
        return buildResponseEntity(apiError);
    }


    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            BusinessException ex) {
        Set<String> messages = new HashSet<>(1);
        messages.add(Translator.toLocale(ex.getMessage()));

        ResponseModel apiError = new ResponseModel(NOT_ACCEPTABLE);
        List<ApiSubError> subErrors = new ArrayList<>();
        messages.forEach(s -> {
            ApiValidationError subError = new ApiValidationError(null,s);
            subErrors.add(subError);
        });

        apiError.setSubErrors(subErrors);
        logger.info("Response:"+apiError.getMessage());
        return buildResponseEntity(apiError);
    }
    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            NoSuchElementException ex) {
        ResponseModel apiError = new ResponseModel(NOT_FOUND);
        apiError.setMessage("اطلاعات مورد نظر یافت نگردید");
        logger.info("Response:"+apiError.getMessage());
        return buildResponseEntity(apiError);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

        Set<String> messages = new HashSet<>(constraintViolations.size());
        messages.addAll(constraintViolations.stream()
                .map(constraintViolation -> String.format("%s", Translator.toLocale(constraintViolation.getMessage())))
                .collect(Collectors.toList()));

        ResponseModel apiError = new ResponseModel(NOT_ACCEPTABLE);
        apiError.setMessage(Translator.toLocale("constraint.validation.exception"));
        List<ApiSubError> subErrors = new ArrayList<>();
        messages.forEach(s -> {
            ApiValidationError subError = new ApiValidationError(null,s);
            subErrors.add(subError);
        });

        apiError.setSubErrors(subErrors);
        logger.info("Response:"+messages);
        return buildResponseEntity(apiError);

    }
}
