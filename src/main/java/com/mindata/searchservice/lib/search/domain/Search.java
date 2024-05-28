package com.mindata.searchservice.lib.search.domain;

import com.mindata.searchservice.lib.search.domain.events.SearchCreatedEvent;
import com.mindata.searchservice.lib.search.domain.exceptions.InvalidSearchArgumentException;
import com.mindata.searchservice.lib.shared.domain.AggregateRoot;
import org.checkerframework.checker.units.qual.A;

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
        this.checkOut = checkOut;
        this.checkIn = checkIn;
        this.ages = validateAges(ages);;
    }
    
    private List<Age> validateAges(List<Age> ages) {
        if (ages.isEmpty()) {
            throw new InvalidSearchArgumentException("The list of ages cannot be empty");
        }
        
        return ages.stream().sorted().toList();
    }
    
    public static Search create(
        String searchId, String searchHotelId, String checkIn, String checkOut, List<Integer> searchAges
    ) {
        var searchCheckIn = new CheckIn(checkIn);
        var searchCheckOut = new CheckOut(checkOut);
        
        if (searchAges == null) {
            throw new InvalidSearchArgumentException("Invalid ages, MUST be NOT null");
        }
        
        if (searchCheckIn.compareTo(searchCheckOut) > 0) {
            throw new InvalidSearchArgumentException("Checkout can not be before checkin");
        }
        
        var id = new SearchId(searchId);
        var hotelId = new HotelId(searchHotelId);
        var ages = searchAges.stream().map(Age::new).toList();
        
        var search = new Search(id, hotelId, searchCheckIn, searchCheckOut, ages);
        
        search.record(new SearchCreatedEvent(id.value(),
            search.hotelId.value(),
            search.checkIn.value(),
            search.checkOut.value(),
            Integer.toString(search.hashCode()),
            search.ages.stream().map(Age::value).toList()));
        
        return search;
    }
    
    public SearchId searchId() {
        return id;
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
        return Objects.equals(id, search.id) && Objects.equals(hotelId,
            search.hotelId) && Objects.equals(checkIn, search.checkIn) && Objects.equals(checkOut,
            search.checkOut) && Objects.equals(ages, search.ages);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, hotelId, checkIn, checkOut, ages);
    }
    
    @Override
    public String toString() {
        return "Search{" +
               "id=" + id +
               ", hotelId=" + hotelId +
               ", checkIn=" + checkIn +
               ", checkOut=" + checkOut +
               ", ages=" + ages +
               '}';
    }
}
