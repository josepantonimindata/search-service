package com.mindata.searchservice.search.application;

import com.mindata.searchservice.search.domain.*;

import java.util.List;

public final class SearchCommandMother {
    public static SearchCommand create(
            String searchId,
            String hotelId,
            String checkIn,
            String checkOut,
            List<Integer> ages
    ) {
        return new SearchCommand(
                searchId,
                hotelId,
                checkIn,
                checkOut,
                ages
        );
    }

    public static SearchCommand random() {
        return create(
                SearchIdMother.random().value(),
                HotelIdMother.random().value(),
                CheckInMother.today().value(),
                CheckOutMother.tomorrow().value(),
                AgeMother.randomList().stream().map(Age::value).toList()
        );
    }

    public static SearchCommand randomWithId(String id) {
        return create(
                SearchIdMother.create(id).value(),
                HotelIdMother.random().value(),
                CheckInMother.today().value(),
                CheckOutMother.tomorrow().value(),
                AgeMother.randomList().stream().map(Age::value).toList()
        );
    }

    // TODO: Create a Random String Generator
    public static SearchCommand randomInvalid() {
        return create(
                "Some",
                "invalid",
                "Stuff",
                "for",
                List.of(20,30)
        );
    }
}
