package com.mindata.searchservice.search.infrastructure.SearchPostControllerTest;

import org.junit.jupiter.api.Test;

public final class PublishesEventsToKafka extends SearchPostControllerShould{

    @Test
    public void publishesEventsToKafka() throws Exception {
        // if no error is thrown event should be published
        getCommandAndMakeRequest();
    }
}
