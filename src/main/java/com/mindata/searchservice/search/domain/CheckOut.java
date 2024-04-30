package com.mindata.searchservice.search.domain;

import java.time.LocalDate;
import java.util.Objects;

public final class CheckOut {

    private LocalDate value;

    public CheckOut(LocalDate value) {
        this.value = value;
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
