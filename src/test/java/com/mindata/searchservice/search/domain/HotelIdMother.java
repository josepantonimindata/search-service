package com.mindata.searchservice.search.domain;

import com.mindata.searchservice.shared.domain.UuidMother;

public final class HotelIdMother {
    public static HotelId create(String value) {
        return new HotelId(value);
    }

    public static HotelId random() {
        return new HotelId(UuidMother.random());
    }
}
