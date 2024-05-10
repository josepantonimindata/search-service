//package com.mindata.searchservice.search.infrastructure;
//
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.test.utils.KafkaTestUtils;
//import org.testcontainers.containers.KafkaContainer;
//import org.testcontainers.utility.DockerImageName;
//
//import java.util.Map;
//import java.util.Objects;
//import java.util.Properties;
//
//@TestConfiguration
//public class KafkaTestConfig {
//    private final KafkaContainer kafkaContainer;
//
//    public KafkaTestConfig() {
//        kafkaContainer =  new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.6.1")).withEmbeddedZookeeper();
//        kafkaContainer.start();
//    }
//
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate() {
//        String bootstrapServers = kafkaContainer.getBootstrapServers();
//
//        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(KafkaTestUtils.producerProps(bootstrapServers));
//        return new KafkaTemplate<>(producerFactory);
//    }
//
//    @Bean
//    public KafkaConsumer<String, String> kafkaConsumer() {
//        Properties properties = new Properties();
//        var hashMap = KafkaTestUtils.consumerProps("test", "true", kafkaContainer.getBootstrapServers());
//        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
//            properties.setProperty(entry.getKey(), (String) entry.getValue());
//        }
//        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
//        return kafkaConsumer;
//    }
//}
