package com.mindata.searchservice.search.domain;

import java.time.LocalDate;

public class TimeVariables {
    public static final String TODAY = LocalDate.now().toString();
    public static final String TOMORROW = LocalDate.now().plusDays(1).toString();
    public static final String YESTERDAY = LocalDate.now().minusDays(1).toString();
}
