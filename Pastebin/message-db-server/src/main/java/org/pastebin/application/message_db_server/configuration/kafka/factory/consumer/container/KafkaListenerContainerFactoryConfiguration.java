package org.pastebin.application.message_db_server.configuration.kafka.factory.consumer.container;

import org.pastebin.application.message_db_server.model.MessageTransactionModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
public class KafkaListenerContainerFactoryConfiguration {
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
