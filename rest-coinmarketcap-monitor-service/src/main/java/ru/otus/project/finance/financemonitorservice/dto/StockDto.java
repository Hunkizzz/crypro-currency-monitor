package ru.otus.project.finance.financemonitorservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {
    @JsonProperty("T")
    String symbol;

    @JsonProperty("v")
    long volume;

    @JsonProperty("vw")
    double volumeWeightedAverage;

    @JsonProperty("o")
    double openingPrice;

    @JsonProperty("c")
    double closingPrice;

    @JsonProperty("h")
    double highestPrice;

    @JsonProperty("l")
    double lowestPrice;

    @JsonProperty("t")
    long timestamp;

    @JsonProperty("n")
    int numberOfTransactions;
}