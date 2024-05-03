package com.mindata.searchservice.search.domain;

import java.time.LocalDate;
import java.util.Random;

public final class CheckOutMother {
    private static final Random random = new Random();

    public static CheckOut create(String value) {
        return new CheckOut(value);
    }

    public static CheckOut random() {
        return new CheckOut(LocalDate.ofYearDay(random.nextInt(1980, 2200), random.nextInt(1, 365)).toString());
    }

    public static CheckOut today() {
        return new CheckOut(LocalDate.now().toString());
    }

    public static CheckOut tomorrow() {
        return new CheckOut(LocalDate.now().plusDays(1).toString());
    }

    public static CheckOut yesterday() {
        return new CheckOut(LocalDate.now().minusDays(1).toString());
    }
}
