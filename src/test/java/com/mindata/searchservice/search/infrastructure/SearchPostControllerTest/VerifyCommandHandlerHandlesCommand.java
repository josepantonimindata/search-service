package com.mindata.searchservice.search.infrastructure.SearchPostControllerTest;

import com.mindata.searchservice.search.application.SearchCommandHandler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public final class VerifyCommandHandlerHandlesCommand extends SearchPostControllerShould{
    @MockBean
    private SearchCommandHandler commandHandler;

    @Test
    public void verifyCommandHandlerHandlesCommand() throws Exception{
        var command = getCommandAndMakeRequest();
        verify(commandHandler, atLeastOnce()).handle(command);
    }
}
