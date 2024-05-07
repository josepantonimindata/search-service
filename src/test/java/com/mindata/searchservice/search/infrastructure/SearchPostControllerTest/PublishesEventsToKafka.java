package com.mindata.searchservice.search.infrastructure.SearchPostControllerTest;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.assertj.core.api.Assertions.assertThat;

@EmbeddedKafka(partitions = 1, topics = "hotel_availability_searches")
@EnableKafka
@DirtiesContext
public final class PublishesEventsToKafka extends SearchPostControllerShould{
    private Consumer consumer = new Consumer();

    @Test
    public void publishesEventsToKafka() throws Exception {
        // if no error is thrown event should be published
        getCommandAndMakeRequest();
        assertThat(consumer.messagesReceived()).isEqualTo(1);
    }

    @KafkaListener(topics = "hotel_availability_searches", groupId = "test-group")
    public static class Consumer {
        private final ConcurrentLinkedQueue<ConsumerRecord<String, String>> messages = new ConcurrentLinkedQueue<>();

        public Consumer() {
        }

        @org.springframework.kafka.annotation.KafkaListener(topics = "hotel_availability_searches", groupId = "test-group")
        public void listen(ConsumerRecord<String, String> record) {
            messages.add(record);
        }

        public int messagesReceived() {
            return messages.size();
        }

        public ConsumerRecord<String, String> getReceivedMessage(int index) {
            return new ArrayList<>(messages).get(index);
        }
    }
}
