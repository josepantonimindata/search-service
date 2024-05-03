package com.mindata.searchservice.search.domain;

import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import static com.mindata.searchservice.search.domain.TimeVariables.TODAY;
import static com.mindata.searchservice.search.domain.TimeVariables.TOMORROW;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SearchCreatedEventTests {
    
    @Test
    public void createEvent() {
        var event = new SearchCreatedEvent(
            "be4d5ae7-dbbe-4c30-9943-fefbe631c8f4",
            "1",
            TODAY,
            TOMORROW,
            "231213",
            List.of()
        );
        
        var diffEvent = new SearchCreatedEvent(
            "be4d5ae7-dbbe-4c30-9943-fefbe631c8f4",
            "1",
            TODAY,
            TOMORROW,
            "231213",
            List.of()
        );
        
        assertNotEquals(event, diffEvent);
    }
    
    @Test
    public void testToFromPrimitives() {
        String aggregateId = "testAggregateId";
        String hotelId = "testHotelId";
        String hash = "testHash";
        List<Integer> ages = List.of(12, 15, 18);
        
        SearchCreatedEvent originalEvent = new SearchCreatedEvent(aggregateId, hotelId, TODAY, TODAY, hash, ages);
        
        HashMap<String, Serializable> serializedData = originalEvent.toPrimitives();
        
        SearchCreatedEvent deserializedEvent = (SearchCreatedEvent) originalEvent.fromPrimitives(
            aggregateId, serializedData, originalEvent.eventId(), originalEvent.occurredOn()
        );
        
        System.out.println(originalEvent.eventId());
        System.out.println(deserializedEvent.eventId());
        assertEquals(originalEvent, deserializedEvent);
    }
}
