package org.pastebin.application.user_server.configurations.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.pastebin.application.user_server.models.MessageTransactionModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.JacksonUtils;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {
    private final String KAFKA_SAVE_TOPIC;
    private final String KAFKA_DELETE_TOPIC;
    private final String KAFKA_BOOTSTRAP;

    public KafkaConfiguration(@Value("${kafka.topic.save}") String KAFKA_SAVE_TOPIC, @Value("${kafka.topic.delete}") String KAFKA_DELETE_TOPIC, @Value("${kafka.bootstrap}") String KAFKA_BOOTSTRAP) {
        this.KAFKA_SAVE_TOPIC = KAFKA_SAVE_TOPIC;
        this.KAFKA_DELETE_TOPIC = KAFKA_DELETE_TOPIC;
        this.KAFKA_BOOTSTRAP = KAFKA_BOOTSTRAP;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return JacksonUtils.enhancedObjectMapper();
    }

    @Bean
    public ProducerFactory<String, MessageTransactionModel> producerFactory() {
        Map<String, Object> properties = new HashMap<>();

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BOOTSTRAP);

        var producerFactory = new DefaultKafkaProducerFactory<String, MessageTransactionModel>(properties);
        producerFactory.setValueSerializer(new JsonSerializer<>(objectMapper()));

        return producerFactory;
    }

    @Bean
    public KafkaTemplate<String, MessageTransactionModel> kafkaTemplate(ProducerFactory<String, MessageTransactionModel> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }


    @Bean
    public NewTopic saveTopic() {
        return TopicBuilder
                .name(KAFKA_SAVE_TOPIC)
                .build();
    }

    @Bean
    public NewTopic deleteTopic() {
        return TopicBuilder
                .name(KAFKA_DELETE_TOPIC)
                .build();
    }
}