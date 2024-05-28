package org.pastebin.application.api_server.configurations.web_client;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {
    @Bean
    @LoadBalanced
    public WebClient webClient() {
        return WebClient
                .builder()
                .build();
    }

}
