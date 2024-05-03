package com.mindata.searchservice.shared.infrastructure.bus.event.kafka;

import com.mindata.searchservice.shared.domain.bus.event.DomainEvent;
import com.mindata.searchservice.shared.infrastructure.bus.event.DomainEventJsonSerializer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public final class KafkaPublisher {
    private final KafkaTemplate<String, Serializable> template;
    
    public KafkaPublisher(KafkaTemplate<String, Serializable> template) {
        this.template = template;
    }
    
    public void publish(DomainEvent event, String topic) {
        final var serialized = DomainEventJsonSerializer.serialize(event);
        
        template.send(topic, serialized);
    }
}
