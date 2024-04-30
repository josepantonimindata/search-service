package com.mindata.searchservice.shared.infrastructure.bus.event.kafka;

import com.mindata.searchservice.shared.domain.Service;
import com.mindata.searchservice.shared.domain.bus.event.DomainEvent;
import com.mindata.searchservice.shared.domain.bus.event.EventBus;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

@Service
public final class KafkaPublisher {
    private KafkaTemplate template;

    public KafkaPublisher(KafkaTemplate template) {
        this.template = template;
    }

    public void publish(String event) {
//        template.
    }
}
