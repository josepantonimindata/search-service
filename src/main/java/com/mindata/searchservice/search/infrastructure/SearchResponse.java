package com.mindata.searchservice.search.infrastructure;

import com.fasterxml.jackson.annotation.JsonGetter;

public class SearchResponse {
    private String searchId;

    public SearchResponse(String searchId) {
        this.searchId = searchId;
    }

    @JsonGetter
    public String searchId() {
        return searchId;
    }
}
