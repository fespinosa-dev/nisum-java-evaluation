package com.nisum.evaluation.controller;

import com.nisum.evaluation.dto.AuthenticationRequest;
import com.nisum.evaluation.dto.AuthenticationResponse;
import com.nisum.evaluation.model.User;
import com.nisum.evaluation.service.JwtTokenService;
import com.nisum.evaluation.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.Instant;
import java.util.HashMap;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth", produces = APPLICATION_JSON_VALUE)
public class AuthController {
    public static final String MESSAGE = "message";
    public static final String INVALID_CREDENTIALS = "Invalid Credentials";
    public static final String USER_IS_DISABLED = "User is disabled";
    public static final String SOMETHING_WENT_WRONG = "Something went wrong";
    private final Log logger = LogFactory.getLog(getClass());

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;


    @Operation(summary = "Authenticates a user in the system.",
            description = "Authenticates a user in the system and a token for accessing protected resources is returned."
    )
    @ApiResponse(
            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AuthenticationResponse.class))
    )
    @ResponseStatus(OK)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationRequest request) {
        var responseMap = new HashMap<>();
        var username = request.getUsername();
        var password = request.getPassword();

        try {
            var auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username,password));

            if (auth.isAuthenticated()) {
                logger.info("Logged In");


                User user = userService.findUserByName(username);
                String generatedToken = jwtTokenService.generateToken(user.getName());
                user.setToken(generatedToken);
                user.setLastLogin(Instant.now());
                User updatedUser = userService.updateUser(user);

                return ResponseEntity.ok(new AuthenticationResponse(updatedUser));

            } else {
                responseMap.put(MESSAGE, INVALID_CREDENTIALS);
                return ResponseEntity.status(UNAUTHORIZED).body(responseMap);
            }
        } catch (DisabledException e) {
            logger.error(e);
            responseMap.put(MESSAGE, USER_IS_DISABLED);
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(responseMap);
        } catch (BadCredentialsException e) {
            logger.error(e);
            responseMap.put(MESSAGE, INVALID_CREDENTIALS);
            return ResponseEntity.status(UNAUTHORIZED).body(responseMap);
        } catch (Exception e) {
            logger.error(e);
            responseMap.put(MESSAGE, SOMETHING_WENT_WRONG);
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(responseMap);
        }
    }

}
