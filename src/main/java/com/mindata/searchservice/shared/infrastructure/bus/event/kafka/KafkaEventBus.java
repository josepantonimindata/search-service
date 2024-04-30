package com.mindata.searchservice.shared.infrastructure.bus.event.kafka;

import com.mindata.searchservice.shared.domain.bus.event.DomainEvent;
import com.mindata.searchservice.shared.domain.bus.event.EventBus;

import java.util.List;

public final class KafkaEventBus implements EventBus {

    private KafkaPublisher kafkaPublisher;

    public KafkaEventBus(KafkaPublisher kafkaPublisher) {
        this.kafkaPublisher = kafkaPublisher;
    }

    @Override
    public void publish(List<DomainEvent> events) {

    }

//    private void publish()
}
