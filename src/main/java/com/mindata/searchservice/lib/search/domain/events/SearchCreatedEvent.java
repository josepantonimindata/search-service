package com.mindata.searchservice.lib.search.domain.events;

import com.mindata.searchservice.lib.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public final class SearchCreatedEvent extends DomainEvent {
    private final String hotelId;
    private final String checkIn;
    private final String checkOut;
    private final String hash;
    private final List<Integer> ages;
    
    public SearchCreatedEvent(
        String aggregateId,
        String hotelId,
        String checkIn,
        String checkOut,
        String hash,
        List<Integer> ages
    ) {
        super(aggregateId);
        
        this.hotelId = hotelId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.hash = hash;
        this.ages = ages;
    }
    
    private SearchCreatedEvent(
        String aggregateId,
        String eventId,
        String occurredOn,
        String hotelId,
        String checkIn,
        String checkOut,
        String hash,
        List<Integer> ages
    ) {
        super(aggregateId, eventId, occurredOn);
        
        this.hotelId = hotelId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.hash = hash;
        this.ages = ages;
    }
    
    @Override
    public String eventName() {
        return "hotel_availability_searches";
    }
    
    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {{
            put("search", new HashMap<>() {{
                put("hotelId", hotelId);
                put("checkIn", checkIn);
                put("checkOut", checkOut);
                put("ages", ages.toArray(new Integer[0]));
            }});
            put("hash", hash);
        }};
    }
    
    @Override
    public DomainEvent fromPrimitives(
        String aggregateId,
        HashMap<String, Serializable> body,
        String eventId,
        String occurredOn
    ) {
        var map = (HashMap<String, Serializable>) body.get("search");
        return new SearchCreatedEvent(
            aggregateId,
            eventId,
            occurredOn,
            (String) map.get("hotelId"),
            (String) map.get("checkIn"),
            (String) map.get("checkOut"),
            (String) body.get("hash"),
            Arrays.stream(((Integer[]) map.get("ages"))).toList()
        );
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchCreatedEvent that = (SearchCreatedEvent) o;
        return Objects.equals(hotelId, that.hotelId) && Objects.equals(checkIn,
            that.checkIn) && Objects.equals(checkOut, that.checkOut) && Objects.equals(hash,
            that.hash) && Objects.equals(ages, that.ages);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(hotelId, checkIn, checkOut, hash, ages);
    }
}
