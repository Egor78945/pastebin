package org.pastebin.application.user_server.configurations.kafka.topics;

import org.apache.kafka.clients.admin.NewTopic;
import org.pastebin.application.user_server.configurations.kafka.KafkaDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {
    private final KafkaDetails kafkaDetails;

    @Autowired
    public KafkaTopicConfiguration(KafkaDetails kafkaDetails) {
        this.kafkaDetails = kafkaDetails;
    }

    @Bean
    public NewTopic saveTopic() {
        return TopicBuilder
                .name(kafkaDetails.getKAFKA_SAVE_TOPIC())
                .build();
    }

    @Bean
    public NewTopic deleteTopic() {
        return TopicBuilder
                .name(kafkaDetails.getKAFKA_DELETE_TOPIC())
                .build();
    }
}
