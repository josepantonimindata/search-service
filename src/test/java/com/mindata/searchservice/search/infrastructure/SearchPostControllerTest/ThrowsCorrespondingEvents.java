package com.mindata.searchservice.search.infrastructure.SearchPostControllerTest;

import com.mindata.searchservice.search.domain.SearchCreatedEventMother;
import com.mindata.searchservice.search.domain.SearchMother;
import com.mindata.searchservice.shared.domain.bus.event.DomainEvent;
import com.mindata.searchservice.shared.domain.bus.event.EventBus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public final class ThrowsCorrespondingEvents extends SearchPostControllerShould {
    @MockBean
    private EventBus eventBus;

    @Test
    public void eventBusRecievesCorrespondingEvents() throws Exception {
        var command = getCommandAndMakeRequest();

        var event = SearchCreatedEventMother.fromSearch(SearchMother.fromCommand(command));
        shouldHavePublished(event);
    }



    private void shouldHavePublished(List<DomainEvent> domainEvents) {
        verify(eventBus, atLeastOnce()).publish(domainEvents);
    }

    private void shouldHavePublished(DomainEvent domainEvent) {
        shouldHavePublished(Collections.singletonList(domainEvent));
    }
}
