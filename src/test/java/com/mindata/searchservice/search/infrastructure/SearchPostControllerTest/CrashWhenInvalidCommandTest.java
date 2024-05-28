package com.mindata.searchservice.search.infrastructure.SearchPostControllerTest;

import com.mindata.searchservice.lib.shared.domain.bus.event.EventBus;
import com.mindata.searchservice.lib.shared.domain.services.JsonService;
import com.mindata.searchservice.search.application.SearchCommandMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.HashMap;

import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public final class CrashWhenInvalidCommandTest extends SearchPostControllerShould {
    @MockBean
    private EventBus eventBus;
    
    public CrashWhenInvalidCommandTest(@Autowired JsonService service) {super(service);}
    
    @Test
    public void crashWhenInvalidCommand() throws Exception {
        var command = SearchCommandMother.randomInvalid();
        
        var json = jsonService.jsonEncode(new HashMap<>() {{
            put("hotelId", command.hotelId());
            put("checkIn", command.checkIn());
            put("checkOut", command.checkOut());
            put("ages", command.ages().toArray());
        }}).get();
        
        // Check status code is 500 created: ✓
        mockMvc.perform(post("/search").content(json).contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpectAll(
                status().isUnprocessableEntity()
            );
        
        shouldHaveNotPublished();
    }
    
    private void shouldHaveNotPublished() {
        verifyNoInteractions(eventBus);
    }
    
}
