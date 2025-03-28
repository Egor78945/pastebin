package org.pastebin.application.user_server.configuration.web_client;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {
    @Bean
    @LoadBalanced
    public WebClient.Builder webClient() {
        return WebClient
                .builder();
    }

}
