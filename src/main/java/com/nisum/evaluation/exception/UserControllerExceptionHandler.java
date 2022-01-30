package com.nisum.evaluation.exception;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ControllerAdvice
public class UserControllerExceptionHandler {

    @ApiResponse(responseCode = "409", description = "Email already registered",
            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ApiError.class))
    )
    @ExceptionHandler({EmailAlreadyExistsException.class})
    public ResponseEntity<ApiError> handleEmailAlreadyExists(final EmailAlreadyExistsException ex) {
        return new ResponseEntity<>(new ApiError(ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ApiResponse(responseCode = "400", description = "Invalid field supplied",
            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ApiError.class))
    )
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex) {
        var apiErrorResponses = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new ApiError(err.getDefaultMessage()));
        return new ResponseEntity<>(apiErrorResponses, HttpStatus.BAD_REQUEST);
    }


}
