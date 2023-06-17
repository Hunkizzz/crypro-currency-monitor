package ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class Btc {
    @JsonProperty("id")
    int id;

    @JsonProperty("name")
    String name;

    @JsonProperty("symbol")
    String symbol;
}
