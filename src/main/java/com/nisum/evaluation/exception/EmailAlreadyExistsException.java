package com.nisum.evaluation.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException(DataIntegrityViolationException cause) {
        super("El correo ya esta registrado", cause);
    }
}
