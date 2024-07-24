package org.pastebin.application.user_server.configurations.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.JacksonUtils;
import org.springframework.stereotype.Component;

@Component
public class KafkaDetails {
    private final String KAFKA_SAVE_TOPIC;
    private final String KAFKA_DELETE_TOPIC;
    private final String KAFKA_HASH_REQUEST_TOPIC;
    private final String KAFKA_HASH_RESPONSE_TOPIC;
    private final String KAFKA_BOOTSTRAP;

    public KafkaDetails(@Value("${kafka.topic.save}") String KAFKA_SAVE_TOPIC,
                        @Value("${kafka.topic.delete}") String KAFKA_DELETE_TOPIC,
                        @Value("${kafka.bootstrap}") String KAFKA_BOOTSTRAP,
                        @Value("${kafka.topic.hash.request}") String KAFKA_HASH_REQUEST_TOPIC,
                        @Value("${kafka.topic.hash.response}") String KAFKA_HASH_RESPONSE_TOPIC) {
        this.KAFKA_SAVE_TOPIC = KAFKA_SAVE_TOPIC;
        this.KAFKA_DELETE_TOPIC = KAFKA_DELETE_TOPIC;
        this.KAFKA_HASH_REQUEST_TOPIC = KAFKA_HASH_REQUEST_TOPIC;
        this.KAFKA_HASH_RESPONSE_TOPIC = KAFKA_HASH_RESPONSE_TOPIC;
        this.KAFKA_BOOTSTRAP = KAFKA_BOOTSTRAP;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return JacksonUtils.enhancedObjectMapper();
    }

    public String getKAFKA_SAVE_TOPIC() {
        return KAFKA_SAVE_TOPIC;
    }

    public String getKAFKA_DELETE_TOPIC() {
        return KAFKA_DELETE_TOPIC;
    }

    public String getKAFKA_BOOTSTRAP() {
        return KAFKA_BOOTSTRAP;
    }

    public String getKAFKA_HASH_REQUEST_TOPIC() {
        return KAFKA_HASH_REQUEST_TOPIC;
    }

    public String getKAFKA_HASH_RESPONSE_TOPIC() {
        return KAFKA_HASH_RESPONSE_TOPIC;
    }
}
