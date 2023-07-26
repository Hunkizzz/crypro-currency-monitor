package ru.otus.project.finance.financemonitorservice;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableConfigurationProperties
@EnableScheduling
@Log4j2
@SpringBootApplication
public class CoinmarketcapMonitorServiceApplication {

    public static void main(String[] args) {
        log.debug("+main()");
        SpringApplication.run(CoinmarketcapMonitorServiceApplication.class, args);
        log.debug("-main()");
    }
}
