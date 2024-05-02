package com.mindata.searchservice.search.domain;

public final class Age {
    public enum PersonType {
        BABY,
        CHILD,
        ADULT
    }

    private Integer value;

    public Age(Integer value) {
        checkAgeRange(value);
        this.value = value;
    }

    private void checkAgeRange(Integer value) {
        if (value > 140 || value < 0) {
            throw new IllegalArgumentException("Invalid Age range");
        }
    }

    public PersonType personType() {
        if (value >= 0 && value < 3) {
           return PersonType.BABY;
        }else if (value >= 3 && value < 12){
            return PersonType.CHILD;
        }else if (value >= 12 && value < 140){
            return PersonType.ADULT;
        } else {
            throw new IllegalArgumentException("Invalid age");
        }
    }

    public Integer value() {
        return value;
    }
}
