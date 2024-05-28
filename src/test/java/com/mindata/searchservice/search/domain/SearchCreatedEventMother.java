package com.mindata.searchservice.search.domain;

import com.mindata.searchservice.lib.search.domain.Age;
import com.mindata.searchservice.lib.search.domain.Search;
import com.mindata.searchservice.lib.search.domain.events.SearchCreatedEvent;

import java.util.List;
import java.util.Random;

public final class SearchCreatedEventMother {
    private static final Random random = new Random();

    public static SearchCreatedEvent create(
            String aggregateId,
            String hotelId,
            String checkIn,
            String checkOut,
            String hash,
            List<Integer> ages
    ) {
        return new SearchCreatedEvent(
                aggregateId,
                hotelId,
                checkIn,
                checkOut,
                hash,
                ages
        );
    }

    public static SearchCreatedEvent fromSearch(Search search) {
        return new SearchCreatedEvent(
                search.searchId().value(),
                search.hotelId().value(),
                search.checkIn().value(),
                search.checkOut().value(),
                Integer.toString(search.hashCode()),
                search.ages().stream().map(Age::value).toList()
        );
    }

    public SearchCreatedEvent random() {
        return create(
                SearchIdMother.random().value(),
                HotelIdMother.random().value(),
                CheckInMother.random().value(),
                CheckOutMother.random().value(),
                Integer.toString(new Object().hashCode()),
                AgeMother.randomList().stream().map(Age::value).toList()
        );
    }
}
