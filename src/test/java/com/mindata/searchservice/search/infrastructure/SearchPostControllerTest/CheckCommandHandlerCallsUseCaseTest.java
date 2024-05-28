package com.mindata.searchservice.search.infrastructure.SearchPostControllerTest;

import com.mindata.searchservice.lib.search.application.NewSearchService;
import com.mindata.searchservice.lib.shared.domain.services.JsonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public final class CheckCommandHandlerCallsUseCaseTest extends SearchPostControllerShould {
    
    @MockBean
    private NewSearchService newSearchService;
    
    public CheckCommandHandlerCallsUseCaseTest(@Autowired JsonService service) {
        super(service);
    }
    
    @Test
    public void checkCommandHandlerCallsUseCase() throws Exception {
        var command = getCommandAndMakeRequest();
        verify(newSearchService, atLeastOnce()).newSearch(uuid,
            command.hotelId(),
            command.checkIn(),
            command.checkOut(),
            command.ages());
    }
}
