package com.nisum.evaluation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerExceptionHandler{

    @ExceptionHandler({EmailAlreadyExistsException.class})
    public ResponseEntity<ApiError> handleEmailAlreadyExists(final EmailAlreadyExistsException ex) {
        return new ResponseEntity<>(new ApiError(ex.getMessage()), HttpStatus.CONFLICT);
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex) {
        var apiErrorResponses = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err->new ApiError(err.getDefaultMessage()));
        return new ResponseEntity<>(apiErrorResponses, HttpStatus.BAD_REQUEST);
    }



}
