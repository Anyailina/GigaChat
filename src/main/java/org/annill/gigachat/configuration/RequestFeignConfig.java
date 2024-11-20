package org.annill.gigachat.configuration;


import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class RequestFeignConfig {

    @Bean
    public RequestInterceptor textRequest() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("Accept", "application/json");
        };
    }
}
