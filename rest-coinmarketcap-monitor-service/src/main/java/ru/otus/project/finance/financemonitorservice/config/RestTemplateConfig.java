package ru.otus.project.finance.financemonitorservice.config;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestTemplateConfig {
    @Value("${coinmarketcap.api.host.baseurl}")
    String coinMarketCapApiHost;
    @Value("${fixer.api.host.baseurl}")
    String fixerApiHost;
    @Value("${polygon.api.host.baseurl}")
    String polygonApiHost;

    @Bean("coinMarketCapRestTemplate")
    public RestTemplate coinMarketCapRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(coinMarketCapApiHost));
        return restTemplate;
    }

    @Bean("fixerRestTemplate")
    public RestTemplate fixerRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(fixerApiHost));
        return restTemplate;
    }

    @Bean("polygonRestTemplate")
    public RestTemplate polygonRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(polygonApiHost));
        return restTemplate;
    }

    @Bean
    public CloseableHttpClient httpClient() {
        return HttpClientBuilder.create().build();
    }

    @Bean
    @ConditionalOnMissingBean
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {

        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClient());
        return clientHttpRequestFactory;
    }
}
