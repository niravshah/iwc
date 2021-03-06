package com.infinityworks.test.nns;

import com.infinityworks.test.nns.exceptions.RestTemplateExceptionHandler;
import com.infinityworks.test.nns.interceptors.ApiVersionInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@SpringBootApplication
public class FSAIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(FSAIntegrationApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .additionalInterceptors(Collections.singletonList(new ApiVersionInterceptor()))
                .errorHandler(new RestTemplateExceptionHandler())
                .build();
    }

}
