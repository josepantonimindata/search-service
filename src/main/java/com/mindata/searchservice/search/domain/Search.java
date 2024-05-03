package com.mindata.searchservice.search.domain;

import com.mindata.searchservice.shared.domain.AggregateRoot;

import java.util.List;
import java.util.Objects;

public final class Search extends AggregateRoot {
    private final SearchId id;
    private final HotelId hotelId;
    private final CheckIn checkIn;
    private final CheckOut checkOut;
    private final List<Age> ages;
    
    public Search(SearchId id, HotelId hotelId, CheckIn checkIn, CheckOut checkOut, List<Age> ages) {
        this.id = id;
        this.hotelId = hotelId;
        this.ages = ages;
        this.checkOut = checkOut;
        this.checkIn = checkIn;
    }
    
    public static Search create(
        String searchId,
        String searchHotelId,
        String checkIn,
        String checkOut,
        List<Integer> searchAges
    ) {
        var searchCheckIn = new CheckIn(checkIn);
        var searchChekOut = new CheckOut(checkOut);
        
        if (searchCheckIn.compareTo(searchChekOut) > 0) {
            throw new IllegalArgumentException("Checkout can not be before checkin");
        }
        var id = new SearchId(searchId);
        var hotellid = new HotelId(searchHotelId);
        var ages = searchAges.stream().map(Age::new).toList();
        
        var search = new Search(id, hotellid, searchCheckIn, searchChekOut, ages);
        
        search.record(new SearchCreatedEvent(
            id.value(),
            search.hotelId.getValue(),
            search.checkIn.value(),
            search.checkOut.value(),
            Integer.toString(search.hashCode()),
            search.ages.stream().map(Age::value).toList()
        ));
        
        return search;
        
    }
    
    public HotelId hotelId() {
        return hotelId;
    }
    
    public CheckIn checkIn() {
        return checkIn;
    }
    
    public CheckOut checkOut() {
        return checkOut;
    }
    
    public List<Age> ages() {
        return ages;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Search search = (Search) o;
        return Objects.equals(hotelId, search.hotelId) && Objects.equals(checkIn, search.checkIn) && Objects.equals(
            checkOut,
            search.checkOut) && Objects.equals(ages, search.ages);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(
            hotelId,
            checkIn,
            checkOut,
            ages.stream().sorted().map(Age::personType));
    }
}
