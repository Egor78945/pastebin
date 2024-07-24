package org.pastebin.application.user_server.configurations.kafka.producer_factories;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.pastebin.application.user_server.configurations.kafka.KafkaDetails;
import org.pastebin.application.user_server.models.MessageTransactionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerFactoryConfiguration {
    private final KafkaDetails kafkaDetails;

    @Autowired
    public KafkaProducerFactoryConfiguration(KafkaDetails kafkaDetails) {
        this.kafkaDetails = kafkaDetails;
    }

    @Bean
    public ProducerFactory<String, MessageTransactionModel> MessageTransactionModelProducerFactory(ObjectMapper objectMapper) {
        Map<String, Object> properties = new HashMap<>();

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaDetails.getKAFKA_BOOTSTRAP());

        var producerFactory = new DefaultKafkaProducerFactory<String, MessageTransactionModel>(properties);
        producerFactory.setValueSerializer(new JsonSerializer<>(objectMapper));

        return producerFactory;
    }

    @Bean
    public ProducerFactory<String, String> StringProducerFactory() {
        Map<String, Object> properties = new HashMap<>();

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaDetails.getKAFKA_BOOTSTRAP());

        return new DefaultKafkaProducerFactory<>(properties);
    }
}
