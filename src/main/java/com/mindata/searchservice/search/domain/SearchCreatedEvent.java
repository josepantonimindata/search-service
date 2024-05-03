package com.mindata.searchservice.search.domain;

import com.mindata.searchservice.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
    
    @Override
    public String eventName() {
        return "hotel_availability_searches";
    }
    
    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {{
            put("hotelid", hotelId);
            put("checkIn", checkIn);
            put("checkOut", checkOut);
            put("hash", hash);
            put("ages", ages.toArray());
        }};
    }
    
    @Override
    public DomainEvent fromPrimitives(
        String aggregateId,
        HashMap<String, Serializable> body,
        String eventId,
        String occurredOn
    ) {
        return new SearchCreatedEvent(
            aggregateId,
            (String) body.get("hotelId"),
            (String) body.get("checkIn"),
            (String) body.get("checkOut"),
            (String) body.get("hash"),
            Arrays.stream(((int[]) body.get("ages"))).boxed().toList()
        );
    }
}
