package org.pastebin.application.message_db_server.configuration.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.pastebin.application.message_db_server.models.MessageTransactionModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.JacksonUtils;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {
    private final String BOOTSTRAP;
    private final String AUTO_OFFSET_RESET;

    public KafkaConfiguration(@Value("${kafka.bootstrap}") String BOOTSTRAP, @Value("${kafka.auto-offset-reset}") String AUTO_OFFSET_RESET) {
        this.BOOTSTRAP = BOOTSTRAP;
        this.AUTO_OFFSET_RESET = AUTO_OFFSET_RESET;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return JacksonUtils.enhancedObjectMapper();
    }

    @Bean
    public ConsumerFactory<String, MessageTransactionModel> saveConsumerFactory(ObjectMapper objectMapper) {
        Map<String, Object> properties = new HashMap<>();

        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        properties.put(JsonDeserializer.TYPE_MAPPINGS, "org.pastebin.application.user_server.models.MessageTransactionModel:org.pastebin.application.message_db_server.models.MessageTransactionModel");
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, AUTO_OFFSET_RESET);

        var kafkaConsumerFactory = new DefaultKafkaConsumerFactory<String, MessageTransactionModel>(properties);
        kafkaConsumerFactory.setValueDeserializer(new JsonDeserializer<>(objectMapper));

        return kafkaConsumerFactory;
    }

    @Bean
    public ConsumerFactory<String, String> deleteConsumerFactory() {
        Map<String, Object> properties = new HashMap<>();

        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, AUTO_OFFSET_RESET);

        var kafkaConsumerFactory = new DefaultKafkaConsumerFactory<String, String>(properties);
        kafkaConsumerFactory.setValueDeserializer(new StringDeserializer());

        return kafkaConsumerFactory;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, MessageTransactionModel>> saveListenerContainerFactory(ConsumerFactory<String, MessageTransactionModel> saveConsumerFactory) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, MessageTransactionModel>();

        factory.setConsumerFactory(saveConsumerFactory);

        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> deleteListenerContainerFactory(ConsumerFactory<String, String> saveConsumerFactory) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();

        factory.setConsumerFactory(saveConsumerFactory);

        return factory;
    }

}
