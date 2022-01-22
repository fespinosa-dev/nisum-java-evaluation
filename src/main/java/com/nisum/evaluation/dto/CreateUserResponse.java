package com.nisum.evaluation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nisum.evaluation.validation.Password;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CreateUserResponse {

    @Schema(description = "User's ID, autogenerated for new users", example = "UUID4")
    private UUID userId;

    @Schema(description = "User's name", example = "Juan Rodriguez")
    private String name;

    @Schema(description = "User's email", example = "juan@rodriguez.do")
    @Email(message = "Correo inválido")
    private String email;

    @Schema(description = "User's password", example = "0123456789$abcdefgAB")
    @Password
    private String password;

    @Schema(description = "User's phone list")
    @Builder.Default
    private List<PhoneItem> phones = new ArrayList<>();

    @Schema(description = "User's created date", example = "2021-12-14T23:47:46.164577Z")
    private Instant created;

    @Schema(description = "User's last modified date", example = "2021-12-14T23:47:46.164577Z")
    private Instant modified;

    @Schema(description = "User's last login", example = "2021-12-14T23:47:46.164577Z")
    @JsonProperty("last_login")
    private Instant lastLogin;

    @Schema(description = "User's active state", example = "true")
    @JsonProperty("isactive")
    @Builder.Default
    private boolean active = true;

    @Schema(description = "User's jwt token", example = "yJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKdWFuIFJvZHJpZ3VleiIsImlhdC...")
    private String token;

}
