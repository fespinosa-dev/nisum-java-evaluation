package com.nisum.evaluation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nisum.evaluation.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Data
@AllArgsConstructor
public class AuthenticationResponse {

    public AuthenticationResponse(User user) {
        this.username = user.getName();
        this.lastLogin = user.getLastLogin();
        this.token = user.getToken();
    }

    @NotEmpty
    @Schema(description = "User's name", example = "Juan Rodriguez")
    private String username;

    @Schema(description = "User's last login", example = "2021-12-14T23:47:46.164577Z")
    @JsonProperty("last_login")
    private Instant lastLogin;

    @Schema(description = "User's jwt token", example = "yJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKdWFuIFJvZHJpZ3VleiIsImlhdC...")
    private String token;

}
