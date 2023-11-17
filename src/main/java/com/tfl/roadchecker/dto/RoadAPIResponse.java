package com.tfl.roadchecker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoadAPIResponse {
    @JsonProperty("RoadId")
    private String id;
    private String displayName;
    private String statusSeverity;
    private String statusSeverityDescription;
    private String message;

    private String response;
    private ErrorResponse errorResponse;

    public RoadAPIResponse(String id, String displayName, String statusSeverity, String statusSeverityDescription,ErrorResponse errorResponse) {
        super();
        this.id = id;
        this.displayName = displayName;
        this.statusSeverity = statusSeverity;
        this.statusSeverityDescription= statusSeverityDescription;
        this.errorResponse = errorResponse;
    }
}
