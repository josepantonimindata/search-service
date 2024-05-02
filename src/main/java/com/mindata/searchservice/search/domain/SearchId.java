package com.mindata.searchservice.search.domain;

import java.util.UUID;

public final class SearchId {
    private UUID value;


    public SearchId(String value) {
        try {
            this.value = UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Search id fromat (UUID)");
        }
    }

    public String value() {
        return value.toString();
    }
}
