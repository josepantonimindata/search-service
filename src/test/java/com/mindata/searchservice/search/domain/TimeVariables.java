package com.mindata.searchservice.search.domain;

import java.time.LocalDateTime;

public class TimeVariables {
    public static final String TODAY = LocalDateTime.now().toString();
    public static final String TOMORROW = LocalDateTime.now().plusDays(1).toString();
    public static final String YESTERDAY = LocalDateTime.now().minusDays(1).toString();
}
