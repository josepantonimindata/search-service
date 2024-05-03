package com.mindata.searchservice.search.application;

import com.mindata.searchservice.shared.domain.bus.event.DomainEvent;
import com.mindata.searchservice.shared.domain.bus.event.EventBus;

import java.util.ArrayList;
import java.util.List;

public final class MockEventBus implements EventBus {
    private List<DomainEvent> events;

    public MockEventBus() {
        events = new ArrayList<>();
    }

    @Override
    public void publish(List<DomainEvent> events) {
        this.events.addAll(events) ;
    }

    public List<DomainEvent> events() {
        return events;
    }

    public void flushEvents() {
        events = new ArrayList<>();
    }
}
