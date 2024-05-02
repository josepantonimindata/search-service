package com.mindata.searchservice.search.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Ages {

    private List<Integer> ages;
    private List<String> agesFormated;

    public Ages(List<Integer> ages) {
        this.ages = ages;
        this.agesFormated = getAgesFormated();
    }

    public List<Integer> getAges() {
        return ages;
    }

    public List<String> getAgesFormated() {
        List<String> agesFormated = new ArrayList<>();

        ages.stream().sorted().forEach(n -> {
            if (n >= 0 && n < 3) {
                agesFormated.add("Bebe");
            }else if (n >= 3 && n < 12){
                agesFormated.add("Ninyo");
            }else if (n >= 12 && n < 120){
                agesFormated.add("Adulto");
            } else {
                throw new IllegalArgumentException("Invalid age");
            }
        });
        return agesFormated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ages ages1 = (Ages) o;
        return Objects.equals(ages, ages1.ages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agesFormated);
    }
}
