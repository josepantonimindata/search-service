package com.mindata.searchservice.search.infrastructure.SearchPostControllerTest;

import com.mindata.searchservice.search.application.NewSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public final class CheckCommandHandlerCallsUseCase extends SearchPostControllerShould{

    @MockBean
    private NewSearchService newSearchService;

    @Test
    public void checkCommandHandlerCallsUseCase() throws Exception {
        var command = getCommandAndMakeRequest();
        verify(newSearchService, atLeastOnce()).newSearch(uuid, command.hotelId(), command.checkIn(), command.checkOut(), command.ages());
    }
}
