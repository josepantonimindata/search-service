package com.mindata.searchservice.search.application;

import com.mindata.searchservice.search.domain.Search;
import com.mindata.searchservice.shared.domain.bus.command.CommandHandler;
import com.mindata.searchservice.shared.domain.bus.event.EventBus;
import org.springframework.stereotype.Service;

@Service
public final class SearchCommandHandler implements CommandHandler<SearchCommand> {
    private NewSearchService newSearchService;

    public SearchCommandHandler(NewSearchService newSearchService) {
        this.newSearchService = newSearchService;
    }


    @Override
    public void handle(SearchCommand command) {
        newSearchService.newSearch(
                command.searchId(),
                command.hotelId(),
                command.checkIn(),
                command.checkOut(),
                command.ages()
        );
    }
}
