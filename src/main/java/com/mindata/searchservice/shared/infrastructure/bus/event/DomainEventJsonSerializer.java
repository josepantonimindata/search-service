package com.mindata.searchservice.shared.infrastructure.bus.event;


import com.mindata.searchservice.shared.domain.Utils;
import com.mindata.searchservice.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public final class DomainEventJsonSerializer {
    public static String serialize(DomainEvent domainEvent) {
        HashMap<String, Serializable> attributes = domainEvent.toPrimitives();
        attributes.put("id", domainEvent.aggregateId());

        return Utils.jsonEncode(new HashMap<String, Serializable>() {{
            put("data", new HashMap<String, Serializable>() {{
                put("id", domainEvent.eventId());
                put("type", domainEvent.eventName());
                put("occurred_on", domainEvent.occurredOn());
                put("attributes", attributes);
            }});
            put("meta", new HashMap<String, Serializable>());
        }});
    }
}
