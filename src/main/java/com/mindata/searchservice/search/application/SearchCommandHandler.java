package com.mindata.searchservice.search.application;

import com.mindata.searchservice.search.domain.Search;
import com.mindata.searchservice.shared.domain.AggregateRoot;
import com.mindata.searchservice.shared.domain.bus.command.CommandHandler;
import com.mindata.searchservice.shared.domain.bus.event.EventBus;
import org.springframework.stereotype.Service;

@Service
public final class SearchCommandHandler implements CommandHandler<SearchCommand> {

    private final EventBus bus;

    public SearchCommandHandler(EventBus bus) {this.bus = bus;}

    @Override
    public void handle(SearchCommand command) {
        var search =  Search.create(
                command.searchId(),
                command.hotelId(),
                command.checkIn(),
                command.checkOut(),
                command.ages()
        );

        final var events = search.pullDomainEvents();
        bus.publish(events);
    }
}
