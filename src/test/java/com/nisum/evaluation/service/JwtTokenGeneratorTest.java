package com.nisum.evaluation.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class, classes = JwtTokenGenerator.class)
class JwtTokenGeneratorTest {

    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;

    @Test
    void generateJwtToken() {
        var jwtToken = jwtTokenGenerator.generateJwtToken("Fernando Espinosa");
        assertThat(jwtToken).isNotEmpty();
    }
}