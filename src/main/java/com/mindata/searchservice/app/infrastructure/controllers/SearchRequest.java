package com.mindata.searchservice.app.infrastructure.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SearchRequest(
    @JsonProperty String hotelId,
    @JsonProperty String checkIn,
    @JsonProperty String checkOut,
    @JsonProperty List<Integer> ages
) {}
