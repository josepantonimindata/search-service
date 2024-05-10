package com.mindata.searchservice.shared.domain;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public abstract class DatetimeValueObject implements Comparable<DatetimeValueObject> {
    private final LocalDate value;

    protected DatetimeValueObject(String value) {
        checkIsNotNull(value);
        this.value = checkFormat(value);
    }

    private static void checkIsNotNull(String value) {
        if (value == null) {
            throw new InvalidDateFormatException("Date can not be null");
        }
    }

    private LocalDate checkFormat(String value) {
        final LocalDate dateTime;
        try {
            dateTime = LocalDate.parse(value);
        } catch (DateTimeParseException e) {
            System.out.println();
            throw new InvalidDateFormatException("Invalid date format");
        }
        return dateTime;
    }

    public String value() {
        return value.toString();
    }

    @Override
    public int compareTo(DatetimeValueObject other) {
        return value.compareTo(other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatetimeValueObject that = (DatetimeValueObject) o;
        return Objects.equals(value, that.value);
    }
}
