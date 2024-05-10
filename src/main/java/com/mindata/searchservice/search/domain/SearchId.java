package com.mindata.searchservice.search.domain;

import java.util.Objects;
import java.util.UUID;

public final class SearchId {
    private final UUID value;


    public SearchId(String value) {
        if (value == null) {
            throw new InvalidSearchArgumentException("Invalid Search id must be NOT null");
        }
        try {
            this.value = UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new InvalidSearchArgumentException("Invalid Search id fromat (UUID)");
        }
    }

    public String value() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchId searchId = (SearchId) o;
        return Objects.equals(value, searchId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
