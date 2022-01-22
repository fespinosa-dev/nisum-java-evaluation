package com.nisum.evaluation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Schema(description = "User's phone")
public class PhoneItem {

    @Schema(description = "Phone number of the user.", example = "1234567")
    @Positive
    private String number;

    @Schema(description = "City code of the user.", example = "1")
    @JsonProperty("citycode")
    private String cityCode;

    @Schema(description = "Country code of the user.", example = "57")
    @JsonProperty("countrycode")
    private String countryCode;
}