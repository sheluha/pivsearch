package com.pivseacrh.pivseacrh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {
    private static final String BASE_URL = "https://search-maps.yandex.ru";

    @Bean
    public WebClient webClientWithTimeout() {
        return WebClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }
}
