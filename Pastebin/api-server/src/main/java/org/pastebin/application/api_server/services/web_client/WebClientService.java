package org.pastebin.application.api_server.services.web_client;

import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class WebClientService {
    private final WebClient.Builder webClientBuilder;
    private final EurekaClient eurekaClient;

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

    public <T> T deleteRequest(String url, Class<T> resultClass) {
        return webClientBuilder
                .build()
                .delete()
                .uri(url)
                .retrieve()
                .bodyToMono(resultClass)
                .block();
    }

    public String getHost(String serverName) {
        return eurekaClient
                .getApplication(serverName)
                .getInstances()
                .getFirst()
                .getHostName();
    }

    public int getPort(String serverName) {
        return eurekaClient
                .getApplication(serverName)
                .getInstances()
                .getFirst()
                .getPort();
    }
}
