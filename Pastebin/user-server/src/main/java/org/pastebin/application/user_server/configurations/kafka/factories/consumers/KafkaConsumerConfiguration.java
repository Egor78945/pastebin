package org.pastebin.application.user_server.configurations.kafka.factories.consumers;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.pastebin.application.user_server.configurations.kafka.KafkaDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConsumerProperties;

import java.util.HashMap;
import java.util.List;

@Configuration
public class KafkaConsumerConfiguration {
    private final KafkaDetails kafkaDetails;
    private final ConsumerFactory<String, String> kafkraConsumerFactory;

    @Autowired
    public KafkaConsumerConfiguration(KafkaDetails kafkaDetails, ConsumerFactory<String, String> kafkraConsumerFactory) {
        this.kafkaDetails = kafkaDetails;
        this.kafkraConsumerFactory = kafkraConsumerFactory;
    }

    @Bean
    public KafkaConsumer<String, String> kafkaConsumer() {
        var properties = new HashMap<String, Object>();

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaDetails.getKAFKA_BOOTSTRAP());
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaDetails.getKAFKA_GROUP_ID());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaDetails.getKAFKA_AUTO_OFFSET_RESET());

        return new KafkaConsumer<>(properties);
    }
}
