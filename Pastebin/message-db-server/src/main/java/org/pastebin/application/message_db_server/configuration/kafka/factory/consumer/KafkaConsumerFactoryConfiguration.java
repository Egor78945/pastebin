package org.pastebin.application.message_db_server.configuration.kafka.factory.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.pastebin.application.message_db_server.configuration.kafka.KafkaDetails;
import org.pastebin.application.message_db_server.model.MessageTransactionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerFactoryConfiguration {
    private final KafkaDetails kafkaDetails;

    @Autowired
    public KafkaConsumerFactoryConfiguration(KafkaDetails kafkaDetails) {
        this.kafkaDetails = kafkaDetails;
    }

    @Bean
    public ConsumerFactory<String, MessageTransactionModel> messageTransactionModelConsumerFactory(ObjectMapper objectMapper) {
        Map<String, Object> properties = new HashMap<>();

        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        properties.put(JsonDeserializer.TYPE_MAPPINGS, "org.pastebin.application.user_server.models.MessageTransactionModel:org.pastebin.application.message_db_server.models.MessageTransactionModel");
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaDetails.getKAFKA_BOOTSTRAP());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaDetails.getKAFKA_AUTO_OFFSET_RESET());

        var kafkaConsumerFactory = new DefaultKafkaConsumerFactory<String, MessageTransactionModel>(properties);
        kafkaConsumerFactory.setValueDeserializer(new JsonDeserializer<>(objectMapper));

        return kafkaConsumerFactory;
    }

    @Bean
    public ConsumerFactory<String, String> stringConsumerFactory() {
        Map<String, Object> properties = new HashMap<>();

        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaDetails.getKAFKA_BOOTSTRAP());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaDetails.getKAFKA_AUTO_OFFSET_RESET());

        return new DefaultKafkaConsumerFactory<>(properties);
    }
}
