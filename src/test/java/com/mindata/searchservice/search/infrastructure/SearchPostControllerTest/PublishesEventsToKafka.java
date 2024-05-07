package com.mindata.searchservice.search.infrastructure.SearchPostControllerTest;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.TopicListing;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.core.KafkaAdmin;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
public final class PublishesEventsToKafka extends SearchPostControllerShould{

    @Container
    static final KafkaContainer kafkaContainer = new KafkaContainer(
            DockerImageName.parse("confluentinc/cp-kafka:7.6.1")
    );

    @DynamicPropertySource
    static void dynamicPropertySource(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
    }

    @Autowired
    private KafkaAdmin admin;

    static {
        kafkaContainer.start();
    }

    String topicName = "hotel_availability_searches";

    @Test
    public void publishesEventsToKafka() throws Exception {
        // if no error is thrown event should be published
        getCommandAndMakeRequest();

        AdminClient client = AdminClient.create(admin.getConfigurationProperties());
        Collection<TopicListing> topicList = client.listTopics().listings().get();
        assertNotNull(topicList);
        var a = topicList.stream().filter((topicListing -> topicListing.name().equals(topicName))).toList();
        assertEquals(a.size(), 1);

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test");

        Consumer<String, String> consumer = new KafkaConsumer<>(props);
        TopicPartition topicPartition = new TopicPartition(topicName, 0);
        consumer.assign(Collections.singletonList(topicPartition));

        consumer.seekToBeginning(Collections.singletonList(topicPartition));
        long startOffset = consumer.position(topicPartition);

        consumer.seekToEnd(Collections.singletonList(topicPartition));
        long endOffset = consumer.position(topicPartition);

        long numberOfMessages = endOffset - startOffset;
        System.out.println("Number of messages in topic: " + numberOfMessages);

        consumer.seekToBeginning(Collections.singletonList(topicPartition));
        var records = consumer.poll(Duration.ofMillis(100));

        System.out.println("PRINTING EVENTS");
        records.forEach(record -> System.out.println(record.value()));

        consumer.close();
        assertEquals(numberOfMessages, 1);
    }
}
