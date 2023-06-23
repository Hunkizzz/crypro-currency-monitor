package ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.config;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class restTemplateConfig {
    @Value("${coinmarketcap.api.host.baseurl}")
    String apiHost;

    @Bean
    public RestTemplate restTemplate() {

        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(apiHost));
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
