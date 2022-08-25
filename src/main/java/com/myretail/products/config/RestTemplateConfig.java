package com.myretail.products.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties(prefix = "rest")
public class RestTemplateConfig {

    private int connectionTimeout;
    private int socketTimeout;

    public RestTemplate getRestTemplate(){

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(socketTimeout);
        requestFactory.setReadTimeout(connectionTimeout);
        return new RestTemplate(requestFactory);
    }
}
