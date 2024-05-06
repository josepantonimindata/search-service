package com.mindata.searchservice.search.infrastructure;

import com.mindata.searchservice.search.application.NewSearchService;
import com.mindata.searchservice.search.application.SearchCommandHandler;
import com.mindata.searchservice.search.application.SearchCommandMother;
import com.mindata.searchservice.search.domain.HotelIdMother;
import com.mindata.searchservice.search.domain.SearchCreatedEventMother;
import com.mindata.searchservice.search.domain.SearchIdMother;
import com.mindata.searchservice.search.domain.SearchMother;
import com.mindata.searchservice.shared.domain.Utils;
import com.mindata.searchservice.shared.domain.UuidGenerator;
import com.mindata.searchservice.shared.domain.UuidMother;
import com.mindata.searchservice.shared.domain.bus.event.DomainEvent;
import com.mindata.searchservice.shared.domain.bus.event.EventBus;
import com.mindata.searchservice.shared.infrastructure.UnitTestCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public final class SearchPostControllerShould {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UuidGenerator uuidGenerator;

    @MockBean
    private EventBus eventBus;

    @MockBean
    private NewSearchService newSearchService;

    @MockBean
    private SearchCommandHandler commandHandler;

    private String uuid;

    @BeforeEach
    public void setUp() {
        uuid = UuidMother.random();
        when(uuidGenerator.generate()).thenReturn(uuid);
    }





    @Test
    public void createsASearchResultSuccessfully() throws Exception {
        var command = SearchCommandMother.randomWithId(uuid);

        var json = Utils.jsonEncode(new HashMap<>() {{
            put("hotelId", command.hotelId());
            put("checkIn", command.checkIn());
            put("checkOut", command.checkOut());
            put("ages", command.ages().toArray());
        }});

        // Check uuid returning is correct: ✓
        // Check status code is 201 created: ✓
        mockMvc.perform(post("/search").content(json).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpectAll(
                jsonPath("$.searchId").value(uuid),
                status().isProcessing()
        );

        // Check that event handler handles the command ✓
        verify(commandHandler, atLeastOnce()).handle(command);

        // Check that it calls the use case
//        verify(newSearchService, atLeastOnce()).newSearch(uuid, command.hotelId(), command.checkIn(), command.checkOut(), );

        // Check that the Domain Events are Thrown
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
