package com.mindata.searchservice.shared.infrastructure.bus.event.kafka;

import com.mindata.searchservice.shared.domain.bus.event.DomainEvent;
import com.mindata.searchservice.shared.domain.bus.event.EventBus;

import java.util.List;

public final class KafkaPublisher implements EventBus {
    @Override
    public void publish(List<DomainEvent> events) {
        // publish events to kafka
    }
}
