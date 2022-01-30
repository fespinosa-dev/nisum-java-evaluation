package com.nisum.evaluation.controller;

import com.nisum.evaluation.TestDataFactory;
import com.nisum.evaluation.dto.AuthenticationRequest;
import com.nisum.evaluation.dto.AuthenticationResponse;
import com.nisum.evaluation.service.JwtTokenService;
import com.nisum.evaluation.service.JwtUserDetailsService;
import com.nisum.evaluation.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest extends AbstractControllerTest{

    @MockBean
    private UserService userService;
    @MockBean
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenService jwtTokenService;
    @MockBean
    protected JwtUserDetailsService userDetailsService;


    @Test
    void login() throws Exception {

        var user = TestDataFactory.newUserDetailsUser();
        var nisumUser = TestDataFactory.newUserStub();
        var authentication = Mockito.mock(Authentication.class);


        Mockito.when(userDetailsService.loadUserByUsername("Juan Rodriguez")).thenReturn(user);
        Mockito.when(userService.updateUser(nisumUser)).thenReturn(nisumUser);
        Mockito.when(authenticationManager
                .authenticate(ArgumentMatchers.any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        Mockito.when(authentication.isAuthenticated()).thenReturn(true);

        Mockito.when(userService.findUserByName("Juan Rodriguez")).thenReturn(nisumUser);

        var authRequest = new AuthenticationRequest("Juan Rodriguez", "0123456789$abcdefgAB");
        final var resultActions = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status()
                        .isOk());

        var contentAsString  = resultActions.andReturn().getResponse().getContentAsString();
        var authenticationResponse =
                objectMapper.readValue(contentAsString, AuthenticationResponse.class);

        Assertions.assertThat(authenticationResponse.getToken()).isNotEmpty();
        Assertions.assertThat(authenticationResponse.getToken()).isInstanceOf(String.class);

    }



}