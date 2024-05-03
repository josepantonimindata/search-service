package com.mindata.searchservice.search.domain;

import java.time.LocalDate;
import java.util.Random;

public final class CheckInMother {
    private static final Random random = new Random();

    public static CheckIn create(String value) {
        return new CheckIn(value);
    }

    public static CheckIn random() {
        return new CheckIn(LocalDate.ofYearDay(random.nextInt(1980, 2200), random.nextInt(1, 365)).toString());
    }

    public static CheckIn today() {
        return new CheckIn(LocalDate.now().toString());
    }

    public static CheckIn tomorrow() {
        return new CheckIn(LocalDate.now().plusDays(1).toString());
    }

    public static CheckIn yesterday() {
        return new CheckIn(LocalDate.now().minusDays(1).toString());
    }
}
