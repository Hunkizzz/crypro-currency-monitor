package ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CryptoInfo {
    String name;
    BigDecimal price;
    LocalDateTime timestamp;

    public CryptoInfo(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
