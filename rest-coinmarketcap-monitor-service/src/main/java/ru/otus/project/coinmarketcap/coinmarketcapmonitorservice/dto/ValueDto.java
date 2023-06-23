package ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValueDto {
    @JsonProperty("symbol")
    String symbol;

    @JsonProperty("price")
    BigDecimal price;

    @JsonProperty("volume_24h")
    BigDecimal volume24h;

    @JsonProperty("volume_change_24h")
    BigDecimal volumeChange24h;

    @JsonProperty("percent_change_1h")
    BigDecimal percentChange1h;

    @JsonProperty("percent_change_24h")
    BigDecimal percentChange24h;

    @JsonProperty("percent_change_7d")
    BigDecimal percentChange7d;

    @JsonProperty("percent_change_30d")
    BigDecimal percentChange30d;

    @JsonProperty("percent_change_60d")
    BigDecimal percentChange60d;

    @JsonProperty("percent_change_90d")
    BigDecimal percentChange90d;

    @JsonProperty("market_cap")
    BigDecimal marketCap;

    @JsonProperty("market_cap_dominance")
    BigDecimal marketCapDominance;

    @JsonProperty("fully_diluted_market_cap")
    BigDecimal fullyDilutedMarketCap;

    @JsonProperty("tvl")
    String tvl;

    @JsonProperty("last_updated")
    String lastUpdated;
}