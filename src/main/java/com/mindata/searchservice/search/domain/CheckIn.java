package com.mindata.searchservice.search.domain;

import com.mindata.searchservice.shared.domain.DatetimeValueObject;

import java.time.LocalDate;
import java.util.Objects;

public final class CheckIn extends DatetimeValueObject {

    public CheckIn(String value) {
        super(value);
    }
}
