package ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.properties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConnectionProperties {
    @Value("${coinmarketcap.token}")
    String token;

    @Value("${coinmarketcap.url.listings}")
    String listingsUrl;

    @Value("${coinmarketcap.url.quotes}")
    String quotesUrl;
}
