package com.mindata.searchservice.search.application;

import com.mindata.searchservice.lib.search.application.NewSearchService;
import com.mindata.searchservice.lib.search.application.SearchCommand;
import com.mindata.searchservice.lib.search.application.SearchCommandHandler;
import com.mindata.searchservice.lib.search.domain.SearchCreatedEvent;
import com.mindata.searchservice.search.domain.SearchCreatedEventMother;
import com.mindata.searchservice.search.domain.SearchMother;
import com.mindata.searchservice.lib.shared.domain.DomainError;
import com.mindata.searchservice.shared.infrastructure.UnitTestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public final class SearchCommandHandlerShould extends UnitTestCase {
    private SearchCommandHandler handler;

    @BeforeEach
    public void setUp() {
        super.setUp();

        handler = new SearchCommandHandler(new NewSearchService(eventBus));
    }

    @Test
    public void createANewSearchSuccessfully() {
       var command = SearchCommandMother.random();
       var search = SearchMother.fromCommand(command);
       SearchCreatedEvent event = SearchCreatedEventMother.fromSearch(search);

       handler.handle(command);

       shouldHavePublished(event);
    }

    @Test
    public void shouldThrowAnErrorIfTheCommandContainsInvalidData() {
        SearchCommand command = SearchCommandMother.randomInvalid();
        assertThrows(DomainError.class , () -> handler.handle(command));
    }
}
