package com.mindata.searchservice.search.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class Search {

    private HotelId hotelId;
    private CheckIn checkIn;
    private CheckOut checkOut;
    private Ages ages;

    public Search(HotelId hotelId, Ages ages, CheckOut checkOut, CheckIn checkIn) {
        this.hotelId = hotelId;
        this.ages = ages;
        this.checkOut = checkOut;
        this.checkIn = checkIn;
    }

    public UUID getHotelId() {
        return hotelId.getValue();
    }

    public LocalDate getCheckIn() {
        return checkIn.getValue();
    }

    public LocalDate getCheckOut() {
        return checkOut.getValue();
    }

    public List<String> getAges() {
        return ages.getAgesFormated();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Search search = (Search) o;
        return Objects.equals(hotelId, search.hotelId) && Objects.equals(checkIn, search.checkIn) && Objects.equals(checkOut, search.checkOut) && Objects.equals(ages, search.ages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, checkIn, checkOut, ages);
    }
}
