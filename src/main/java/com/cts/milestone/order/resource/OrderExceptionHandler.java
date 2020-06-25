package com.cts.milestone.order.resource;

import com.cts.milestone.order.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;

@ControllerAdvice
public class OrderExceptionHandler {
    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("INCORRECT_REQUEST", Arrays.asList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PreConditionedFailed.class)
    public final ResponseEntity<ErrorResponse> handlePreConditionFailedException(PreConditionedFailed ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("PRECONDITION_FAILED", Arrays.asList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(error, HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(UnProcessableEntity.class)
    public final ResponseEntity<ErrorResponse> handleUnProcessableEntityException(UnProcessableEntity ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("UNPROCESSABLE_ENTITY", Arrays.asList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(DataConflictException.class)
    public final ResponseEntity<ErrorResponse> handleDataExistsExceptionException(DataConflictException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("DATA_CONFLICT", Arrays.asList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("UNHANDLED_EXCEPTION", Arrays.asList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
