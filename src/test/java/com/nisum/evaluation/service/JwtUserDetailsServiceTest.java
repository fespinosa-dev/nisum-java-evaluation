package com.nisum.evaluation.service;

import com.nisum.evaluation.TestDataFactory;
import com.nisum.evaluation.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtUserDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    @InjectMocks
    private JwtUserDetailsService jwtUserDetailsService;



    @Test
    void loadUserByUsername() {

        var user = TestDataFactory.newUserStub();
        when(mockUserRepository.findUserByName("Juan Rodriguez")).thenReturn(user);
        final UserDetails result = jwtUserDetailsService.loadUserByUsername("Juan Rodriguez");

    }

}
