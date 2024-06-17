package com.alexborza.restclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeoapifyResponse(List<Result> results) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static record Result(
            @JsonProperty("lon") double lon,
            @JsonProperty("lat") double lat
    ) {}
}
