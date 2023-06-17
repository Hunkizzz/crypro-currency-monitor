package ru.otus.project.coinmarketcap.coinmarketcapmonitorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class CoinmarketcapMonitorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoinmarketcapMonitorServiceApplication.class, args);
    }
}
