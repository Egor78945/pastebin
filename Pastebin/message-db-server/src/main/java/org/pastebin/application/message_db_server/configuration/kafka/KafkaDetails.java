package org.pastebin.application.message_db_server.configuration.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaDetails {
    private final String KAFKA_BOOTSTRAP;
    private final String KAFKA_SAVE_TOPIC;
    private final String KAFKA_DELETE_TOPIC;
    private final String KAFKA_AUTO_OFFSET_RESET;
    private final String KAFKA_GROUP_ID;

    public KafkaDetails(@Value("${kafka.bootstrap}") String KAFKA_BOOTSTRAP,
                        @Value("${kafka.topic.save}") String KAFKA_SAVE_TOPIC,
                        @Value("${kafka.topic.delete}") String KAFKA_DELETE_TOPIC,
                        @Value("${kafka.auto-offset-reset}") String KAFKA_AUTO_OFFSET_RESET,
                        @Value("${kafka.groupid}") String KAFKA_GROUP_ID) {
        this.KAFKA_BOOTSTRAP = KAFKA_BOOTSTRAP;
        this.KAFKA_SAVE_TOPIC = KAFKA_SAVE_TOPIC;
        this.KAFKA_DELETE_TOPIC = KAFKA_DELETE_TOPIC;
        this.KAFKA_AUTO_OFFSET_RESET = KAFKA_AUTO_OFFSET_RESET;
        this.KAFKA_GROUP_ID = KAFKA_GROUP_ID;
    }

    public String getKAFKA_GROUP_ID() {
        return KAFKA_GROUP_ID;
    }

    public String getKAFKA_AUTO_OFFSET_RESET() {
        return KAFKA_AUTO_OFFSET_RESET;
    }

    public String getKAFKA_DELETE_TOPIC() {
        return KAFKA_DELETE_TOPIC;
    }

    public String getKAFKA_SAVE_TOPIC() {
        return KAFKA_SAVE_TOPIC;
    }

    public String getKAFKA_BOOTSTRAP() {
        return KAFKA_BOOTSTRAP;
    }
}
