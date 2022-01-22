package com.nisum.evaluation.validation.impl;

import com.nisum.evaluation.validation.Password;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;


public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Value("${nisum.app.password-validator}")
    private String passwordPattern;

    @Override
    public void initialize(Password password) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        var pattern = Pattern.compile(passwordPattern);
        var matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
