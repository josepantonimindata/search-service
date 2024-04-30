package com.mindata.searchservice.search.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ages {

    private List<Integer> ages;

    public Ages(List<Integer> ages) {
        this.ages = ages;
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
            }else {
                agesFormated.add("Adulto");
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
        return Objects.hash(ages);
    }
}
