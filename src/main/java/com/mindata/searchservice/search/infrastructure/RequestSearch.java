package com.mindata.searchservice.search.infrastructure;

import java.util.List;

public record RequestSearch(
    String hotelId,
    String checkIn,
    String checkOut,
    List<Integer> ages
) {
}
