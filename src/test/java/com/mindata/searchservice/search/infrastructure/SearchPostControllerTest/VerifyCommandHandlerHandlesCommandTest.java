package com.mindata.searchservice.search.infrastructure.SearchPostControllerTest;

import com.mindata.searchservice.lib.search.application.SearchCommandHandler;
import com.mindata.searchservice.lib.shared.domain.services.JsonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public final class VerifyCommandHandlerHandlesCommandTest extends SearchPostControllerShould {
    @MockBean
    private SearchCommandHandler commandHandler;
    
    public VerifyCommandHandlerHandlesCommandTest(@Autowired JsonService service) {super(service);}
    
    @Test
    public void verifyCommandHandlerHandlesCommand() throws Exception {
        var command = getCommandAndMakeRequest();
        verify(commandHandler, atLeastOnce()).handle(command);
    }
}
