package ru.otus.project.finance.financemonitorservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "stock_data")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "symbol")
    String symbol;

    @Column(name = "volume")
    long volume;

    @Column(name = "volume_weighted_average")
    double volumeWeightedAverage;

    @Column(name = "opening_price")
    double openingPrice;

    @Column(name = "closing_price")
    double closingPrice;

    @Column(name = "highest_price")
    double highestPrice;

    @Column(name = "lowest_price")
    double lowestPrice;

    @Column(name = "timestamp")
    long timestamp;

    @Column(name = "number_of_transactions")
    int numberOfTransactions;
}
