package com.mindata.searchservice.search.application;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public final class KafkaTestService {
    private ConcurrentLinkedQueue<ConsumerRecord<String, String>> messages = new ConcurrentLinkedQueue<>();

    @KafkaListener(topics = "hotel_availability_searches", groupId = "test")
    public void add(ConsumerRecord<String, String>  message) {
        messages.add(message);
    }

    public int getLenght() {
        return messages.size();
    }
}
