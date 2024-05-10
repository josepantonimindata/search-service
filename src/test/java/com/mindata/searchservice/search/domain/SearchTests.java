package com.mindata.searchservice.search.domain;


import com.mindata.searchservice.shared.domain.InvalidDateFormatException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static com.mindata.searchservice.search.domain.TimeVariables.*;
import static org.junit.jupiter.api.Assertions.*;

public class SearchTests {
    @Test
    public void searchCreateTest() {
        var searchExpected = new Search(
            new SearchId("be4d5ae7-dbbe-4c30-9943-fefbe631c8f4"),
            new HotelId("1"),
            new CheckIn(TODAY),
            new CheckOut(TOMORROW),
            Stream.of(1, 20, 30, 13, 4).map(Age::new).toList()
        );

        var searchObject = Search.create(
            "be4d5ae7-dbbe-4c30-9943-fefbe631c8f4",
            "1",
            TODAY,
            TOMORROW,
            List.of(1, 20, 30, 13, 4)
        );

        var domainEvents = searchObject.pullDomainEvents();

        assertAll("Are Equal",
            () -> assertEquals(searchExpected.toString(), searchObject.toString()),
            () -> assertEquals(searchExpected.hashCode(), searchObject.hashCode()),
            () -> assertEquals(searchExpected.searchRequestHash(), searchObject.searchRequestHash()),
            () -> assertEquals(searchExpected, searchObject)
        );

        assertAll("Getters",
            () -> assertEquals(searchObject.searchId(), searchExpected.searchId()),
            () -> assertEquals(searchObject.hotelId(), searchExpected.hotelId()),
            () -> assertEquals(searchObject.checkIn(), searchExpected.checkIn()),
            () -> assertEquals(searchObject.checkOut(), searchExpected.checkOut()),
            () -> assertEquals(searchObject.ages(), searchExpected.ages())
        );

        assertAll("Event Domain Created",
            () -> assertEquals(domainEvents.size(), 1),
            () -> assertEquals(domainEvents.getFirst().eventName(), "hotel_availability_searches")
        );
    }

    @Test
    public void failSearchCreateTest() {
        assertAll("Failure to Create a Valid Object",
            () -> assertThrows(InvalidSearchArgumentException.class, () -> Search.create(
                "be4d5ae7-dbbe-4c30-9943-fefbe631c8f4",
                "1",
                TODAY,
                YESTERDAY,
                List.of(1, 20, 30, 13, 4)
            )),
            () -> assertThrows(InvalidSearchArgumentException.class, () -> Search.create(
                "1",
                "1",
                TODAY,
                TOMORROW,
                List.of(1, 20, 30, 13, 4)
            )),
            () -> assertThrows(InvalidSearchArgumentException.class, () -> Search.create(
                "be4d5ae7-dbbe-4c30-9943-fefbe631c8f4",
                "1",
                TODAY,
                TOMORROW,
                List.of(200, 20, 30, 13, 4)
            )),
            () -> assertThrows(InvalidSearchArgumentException.class, () -> Search.create(
                "be4d5ae7-dbbe-4c30-9943-fefbe631c8f4",
                "1",
                TODAY,
                TOMORROW,
                List.of(-100, 20, 30, 13, 4)
            )),
            () -> assertThrows(InvalidSearchArgumentException.class, () -> Search.create(
                "be4d5ae7-dbbe-4c30-9943-fefbe631c8f4",
                "",
                TODAY,
                TOMORROW,
                List.of(1, 20, 30, 13, 4)
            )),
            () -> assertThrows(InvalidSearchArgumentException.class, () -> Search.create(
                "be4d5ae7-dbbe-4c30-9943-fefbe631c8f4",
                null,
                TODAY,
                TOMORROW,
                List.of(1, 20, 30, 13, 4)
            )),
            () -> assertThrows(InvalidSearchArgumentException.class, () -> Search.create(
                null,
                "null",
                TODAY,
                TOMORROW,
                List.of(1, 20, 30, 13, 4)
            )),
            () -> assertThrows(InvalidDateFormatException.class, () -> Search.create(
                "be4d5ae7-dbbe-4c30-9943-fefbe631c8f4",
                "1",
                null,
                TOMORROW,
                List.of(1, 20, 30, 13, 4)
            )),
            () -> assertThrows(InvalidDateFormatException.class, () -> Search.create(
                "be4d5ae7-dbbe-4c30-9943-fefbe631c8f4",
                "1",
                TODAY,
                null,
                List.of(1, 20, 30, 13, 4)
            )),
            () -> assertThrows(InvalidSearchArgumentException.class, () -> Search.create(
                "be4d5ae7-dbbe-4c30-9943-fefbe631c8f4",
                "1",
                TODAY,
                TOMORROW,
                null
            ))
        );
    }
}
