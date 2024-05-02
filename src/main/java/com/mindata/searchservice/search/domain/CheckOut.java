package com.mindata.searchservice.search.domain;

import java.time.LocalDate;
import java.util.Objects;

public final class CheckOut {

    private LocalDate value;

    public CheckOut(String value) {
        ensureValueIsDefined(value);

        try {
            this.value = LocalDate.parse(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid date format");
        }
    }

    private void ensureValueIsDefined(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("CheckOut value cannot be null or empty");
        }
    }

    public LocalDate getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckOut checkOut = (CheckOut) o;
        return Objects.equals(value, checkOut.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

}
