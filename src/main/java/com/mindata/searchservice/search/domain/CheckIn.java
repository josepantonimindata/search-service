package com.mindata.searchservice.search.domain;

import java.time.LocalDate;
import java.util.Objects;

public final class CheckIn {

    private LocalDate value;

    public CheckIn(LocalDate value) {
        if (value == null) {
            throw new IllegalArgumentException("CheckIn value cannot be null");
        }
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckIn checkIn = (CheckIn) o;
        return value.equals(checkIn.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
