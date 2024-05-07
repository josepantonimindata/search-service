package com.mindata.searchservice.search.infrastructure.SearchPostControllerTest;
import com.mindata.searchservice.search.application.SearchCommand;
import com.mindata.searchservice.search.application.SearchCommandMother;
import com.mindata.searchservice.shared.domain.Utils;
import com.mindata.searchservice.shared.domain.UuidGenerator;
import com.mindata.searchservice.shared.domain.UuidMother;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class SearchPostControllerShould {
    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected UuidGenerator uuidGenerator;

    protected String uuid;

    @BeforeEach
    public void setUp() {
        uuid = UuidMother.random();
        when(uuidGenerator.generate()).thenReturn(uuid);
    }


    protected SearchCommand getCommandAndMakeRequest() throws Exception {
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
                status().isOk()
        );
        return command;
    }
}
