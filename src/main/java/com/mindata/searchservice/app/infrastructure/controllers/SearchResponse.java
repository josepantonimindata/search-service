package com.mindata.searchservice.app.infrastructure.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SearchResponse(@JsonProperty String searchId) {}
