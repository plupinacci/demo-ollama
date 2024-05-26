package com.example.demoollama.config;

import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.client.RestClientBuilderConfigurer;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
public class CustomConfigs extends OllamaApi {


    @Bean
    public RestClient.Builder restClientBuilder(RestClientBuilderConfigurer restClientBuilderConfigurer) {

        ClientHttpRequestFactorySettings clientHttpRequestFactorySettings = new ClientHttpRequestFactorySettings(
                Duration.ofMillis(50000), Duration.ofMillis(50000), null, null);
        RestClient.Builder builder = RestClient.builder()
                .requestFactory(ClientHttpRequestFactories.get(clientHttpRequestFactorySettings));

        return restClientBuilderConfigurer.configure(builder);
    }

    @Bean
    public OllamaApi ollamaApi(@Value(value = "${spring.ai.ollama.base-url}") String baseUrl) {

        OllamaApi ollamaApi = new OllamaApi(baseUrl);

        return ollamaApi;
    }


}
