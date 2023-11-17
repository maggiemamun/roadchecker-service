package com.tfl.roadchecker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoadCheckerRequest {
    @Valid
    @NotEmpty(message = "roadId should not be blank")
    @Pattern(regexp = "([0-9a-zA-z\\s]{2,10})$")
    private String roadId;
}

