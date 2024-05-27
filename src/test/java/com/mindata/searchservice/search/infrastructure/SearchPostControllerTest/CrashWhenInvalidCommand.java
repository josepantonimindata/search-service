package com.mindata.searchservice.search.infrastructure.SearchPostControllerTest;

import com.mindata.searchservice.search.application.SearchCommandMother;
import com.mindata.searchservice.lib.shared.domain.Utils;
import com.mindata.searchservice.lib.shared.domain.bus.event.EventBus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.HashMap;

import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public final class CrashWhenInvalidCommand extends SearchPostControllerShould {
    @MockBean
    private EventBus eventBus;

    @Test
    public void crashWhenInvalidCommand() throws Exception {
        var command = SearchCommandMother.randomInvalid();

        var json = Utils.jsonEncode(new HashMap<>() {{
            put("hotelId", command.hotelId());
            put("checkIn", command.checkIn());
            put("checkOut", command.checkOut());
            put("ages", command.ages().toArray());
        }});

        // Check status code is 500 created: ✓
        mockMvc.perform(post("/search").content(json).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpectAll(
                status().isBadRequest()
        );

        shouldHaveNotPublished();
    }

    private void shouldHaveNotPublished() {
        verifyNoInteractions(eventBus);
    }

}
