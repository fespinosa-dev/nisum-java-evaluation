package com.nisum.evaluation.dto;

import com.nisum.evaluation.validation.Password;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

    @Schema(description = "User's name", example = "Juan Rodriguez")
    private String username;
    @NotEmpty
    @Schema(description = "User's password", example = "0123456789$abcdefgAB")
    @Password
    private String password;
}
