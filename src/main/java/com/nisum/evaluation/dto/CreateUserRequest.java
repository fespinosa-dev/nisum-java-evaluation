package com.nisum.evaluation.dto;

import com.nisum.evaluation.validation.Password;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    private static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*"
            + "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    @Schema(description = "User's name", example = "Juan Rodriguez")
    private String name;

    @Schema(description = "User's email", example = "juan@rodriguez.do")
    @Email(message = "Correo inválido", regexp = EMAIL_PATTERN)
    private String email;

    @Schema(description = "User's password", example = "0123456789$abcdefgAB")
    @Password
    private String password;

    @Builder.Default
    @Schema(description = "User's phone list")
    private List<PhoneItem> phones = new ArrayList<>();

}
