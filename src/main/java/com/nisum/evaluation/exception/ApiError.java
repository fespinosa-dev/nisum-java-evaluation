package com.nisum.evaluation.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ApiError {
    private final String message;
}
