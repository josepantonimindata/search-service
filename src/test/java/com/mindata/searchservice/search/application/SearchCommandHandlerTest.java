package com.mindata.searchservice.search.application;

import com.mindata.searchservice.shared.domain.bus.event.DomainEvent;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public final class SearchCommandHandlerTest {
    private SearchCommandHandler searchCommandHandler =
            new SearchCommandHandler(new MockEventBus());
    private MockEventBus bus = new MockEventBus();

    @Test
    public void test() {
       bus.publish(new ArrayList<DomainEvent>(){{add(null);}});
        System.out.println(bus.events());
    }


    @Test
    public void test2() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(bus.events());
    }

}
