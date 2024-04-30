package com.mindata.searchservice.shared.infrastructure.bus.event.kafka;

import com.mindata.searchservice.shared.domain.bus.event.DomainEvent;
import com.mindata.searchservice.shared.domain.bus.event.EventBus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class KafkaEventBus implements EventBus {

    private final KafkaPublisher kafkaPublisher;

    public KafkaEventBus(KafkaPublisher kafkaPublisher) {
        this.kafkaPublisher = kafkaPublisher;
    }

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    private void publish(DomainEvent domainEvent) {
        kafkaPublisher.publish(domainEvent, domainEvent.eventName());
    }
}
