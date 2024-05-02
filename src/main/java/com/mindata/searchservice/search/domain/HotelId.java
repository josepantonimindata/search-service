package com.mindata.searchservice.search.domain;

import java.util.Objects;
import java.util.UUID;

public final class HotelId {

    private String value;

    public HotelId(String value) {
        ensureValueIsDefined(value);

        this.value = value;
    }

    private void ensureValueIsDefined(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty");
        }
    }

    public String getValue() {
        return value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelId hotelId = (HotelId) o;
        return Objects.equals(value, hotelId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

}
