package com.mindata.searchservice.lib.search.application;

import com.mindata.searchservice.lib.shared.domain.bus.command.CommandHandler;
import org.springframework.stereotype.Service;

@Service
public final class SearchCommandHandler implements CommandHandler<SearchCommand> {
    private final NewSearchService newSearchService;
    
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
