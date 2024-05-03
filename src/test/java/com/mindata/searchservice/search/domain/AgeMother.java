package com.mindata.searchservice.search.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class AgeMother {
    private static final Random random = new Random();

    public static Age create(int value) {
        return new Age(value);
    }

    public static Age random() {
        return new Age(random.nextInt(0,120));
    }

    public static List<Age> fromValues(int ...values) {
        return Arrays.stream(values).boxed().map(Age::new).toList();
    }

    public static List<Age> randomList() {
        var list = new ArrayList<Age>();
        var limit = random.nextInt(1,10);

        for (int i = 0; i < limit; i++) {
           list.add(new Age(i));
        }

        return list;
    }

    public static List<Age> fromIntegerList(List<Integer> ages) {
        return ages.stream().map(Age::new).toList();
    }
}
