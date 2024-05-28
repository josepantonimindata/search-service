package com.mindata.searchservice.lib.search.domain;

import com.mindata.searchservice.lib.search.domain.exceptions.InvalidSearchArgumentException;
import com.mindata.searchservice.lib.shared.domain.objects.IntValueObject;
import org.springframework.lang.NonNull;

import java.util.Objects;

public class Age extends IntValueObject implements Comparable<Age> {
    
    public Age(@NonNull Integer value) {
        super(value);
        checkAgeRange(value);
    }
    
    private void checkAgeRange(@NonNull Integer value) {
        if (value > 140 || value < 0) {
            throw new InvalidSearchArgumentException("Invalid Age range" + value);
        }
    }
    
    @Override
    public int compareTo(@NonNull Age o) {
        return Integer.compare(value, o.value);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Age age = (Age) o;
        return Objects.equals(value, age.value);
    }
    
}
