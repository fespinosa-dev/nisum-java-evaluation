package com.nisum.evaluation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class JwtTokenGeneratorTest {

    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;

    @Test
    void generateJwtToken() {
        var jwtToken = jwtTokenGenerator.generateJwtToken("Fernando Espinosa");
        assertThat(jwtToken).isNotEmpty();
    }
}