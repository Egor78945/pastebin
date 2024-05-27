package org.pastebin.application.api_server.services.web_client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class WebClientService {
    private final WebClient.Builder webClientBuilder;

    public <T> T getRequest(String url, Class<T> resultClass) {
        return webClientBuilder
                .build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(resultClass)
                .block();
    }

    public <T> T postRequest(String url, Class<T> resultClass) {
        return webClientBuilder
                .build()
                .post()
                .uri(url)
                .retrieve()
                .bodyToMono(resultClass)
                .block();
    }
}
