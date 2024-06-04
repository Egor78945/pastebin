package org.pastebin.application.user_server.configurations.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

//@Configuration
public class KafkaConfiguration {
    private final String KAFKA_SAVE_TOPIC;
    private final String KAFKA_GET_TOPIC;
    private final String KAFKA_BOOTSTRAP;

    public KafkaConfiguration(@Value("${kafka.topic.save}") String KAFKA_SAVE_TOPIC, @Value("${kafka.topic.get}") String KAFKA_GET_TOPIC, @Value("${kafka.bootstrap}") String KAFKA_BOOTSTRAP) {
        this.KAFKA_SAVE_TOPIC = KAFKA_SAVE_TOPIC;
        this.KAFKA_GET_TOPIC = KAFKA_GET_TOPIC;
        this.KAFKA_BOOTSTRAP = KAFKA_BOOTSTRAP;
    }


//    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> properties = new HashMap<>();

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BOOTSTRAP);

        var producerFactory = new DefaultKafkaProducerFactory<String, String>(properties);
        producerFactory.setValueSerializer(new StringSerializer());

        return producerFactory;
    }

//    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }


//    @Bean
    public NewTopic saveTopic() {
        return TopicBuilder
                .name(KAFKA_SAVE_TOPIC)
                .build();
    }
}