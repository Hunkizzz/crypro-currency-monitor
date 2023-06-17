package ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "crypto_currency")
public class CryptoCurrency {
    @Id
    @GeneratedValue
    private UUID uuid;

    private String name;

    private String symbol;

    private double price;
}