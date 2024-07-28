package org.pastebin.application.user_server.configuration.kafka.template;

import org.pastebin.application.user_server.model.MessageTransactionModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaTemplateConfiguration {
    @Bean
    public KafkaTemplate<String, MessageTransactionModel> messageTransactionModelKafkaTemplate(ProducerFactory<String, MessageTransactionModel> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public KafkaTemplate<String, String> stringKafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
