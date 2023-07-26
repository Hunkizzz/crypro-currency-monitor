package ru.otus.project.finance.financemonitorservice.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
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
