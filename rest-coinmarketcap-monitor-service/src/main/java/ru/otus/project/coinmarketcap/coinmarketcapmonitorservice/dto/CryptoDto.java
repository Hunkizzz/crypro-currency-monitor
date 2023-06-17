package ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CryptoDto {
//    @JsonProperty("id")
//    int id;

    @JsonProperty("name")
    String name;

//    @JsonProperty("symbol")
//    String symbol;

    @JsonProperty("insertDate")
    LocalDateTime insertDate;

    @JsonProperty("price")
    BigDecimal price;
}
