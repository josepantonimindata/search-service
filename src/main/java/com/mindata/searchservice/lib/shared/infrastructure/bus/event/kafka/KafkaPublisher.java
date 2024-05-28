package com.mindata.searchservice.lib.shared.infrastructure.bus.event.kafka;

import com.mindata.searchservice.lib.shared.domain.bus.event.DomainEvent;
import com.mindata.searchservice.lib.shared.infrastructure.bus.event.DomainEventJsonSerializer;
import org.springframework.boot.json.JsonParseException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public final class KafkaPublisher {
    private final KafkaTemplate<String, Serializable> template;
    private final DomainEventJsonSerializer serializer;
    
    public KafkaPublisher(KafkaTemplate<String, Serializable> template, DomainEventJsonSerializer serializer) {
        this.template = template;
        this.serializer = serializer;
    }
    
    public void publish(DomainEvent event, String topic) {
        final var serialized = serializer.serialize(event);
        
        if (serialized.isEmpty()) {
            throw new JsonParseException();
        }
        
        template.send(topic, serialized);
    }
}
