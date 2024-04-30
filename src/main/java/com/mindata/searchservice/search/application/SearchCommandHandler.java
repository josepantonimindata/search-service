package com.mindata.searchservice.search.application;

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
        AggregateRoot search = null; // Search.create()

        final var events = search.pullDomainEvents();
        bus.publish(events);
    }
}
