package com.mindata.searchservice.lib.search.application;

import com.mindata.searchservice.lib.search.domain.Search;
import com.mindata.searchservice.lib.shared.domain.bus.event.EventBus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class NewSearchService {
    private final EventBus eventBus;

    public NewSearchService(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void newSearch(String searchId, String hotelId, String checkIn, String checkOut, List<Integer> ages) {
        var search = Search.create(
                searchId,
                hotelId,
                checkIn,
                checkOut,
                ages
        );

        final var events = search.pullDomainEvents();
        eventBus.publish(events);
    }
}
