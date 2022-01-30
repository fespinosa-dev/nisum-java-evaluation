package com.nisum.evaluation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JwtTokenServiceTest {


    @Autowired
    private JwtTokenService jwtTokenService;


    @Test
    void getUsernameFromToken() {
        var token =  jwtTokenService.generateToken("Fernando");
        assertThat(jwtTokenService.getUsernameFromToken(token)).isEqualTo(Optional.of("Fernando"));
    }

    @Test
    void generateToken() {
        assertThat(jwtTokenService.generateToken("userName")).isOfAnyClassIn(String.class);
    }

    @Test
    void validateToken() {
        var token =  jwtTokenService.generateToken("Fernando");
        assertThat(jwtTokenService.validateToken(token, "Fernando")).isTrue();
    }
}
