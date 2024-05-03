package com.mindata.searchservice.search.domain;

import com.mindata.searchservice.search.application.SearchCommand;

import java.util.List;

public final class SearchMother {
    public static Search create(
            String searchId,
            String searchHotelId,
            String checkIn,
            String checkOut,
            List<Integer> searchAges
    ) {
        return new Search(
                SearchIdMother.create(searchId),
                HotelIdMother.create(searchHotelId),
                CheckInMother.create(checkIn),
                CheckOutMother.create(checkOut),
                searchAges.stream().map(AgeMother::create).toList()
        );
    }

    public static Search random() {
        return new Search(
                SearchIdMother.random(),
                HotelIdMother.random(),
                CheckInMother.random(),
                CheckOutMother.random(),
                AgeMother.randomList()
        );
    }

    public static Search fromCommand(SearchCommand searchCommand){
        return new Search(
                SearchIdMother.create(searchCommand.searchId()),
                HotelIdMother.create(searchCommand.hotelId()),
                CheckInMother.create(searchCommand.checkIn()),
                CheckOutMother.create(searchCommand.checkOut()),
                AgeMother.fromIntegerList(searchCommand.ages())
        );
    }
}
