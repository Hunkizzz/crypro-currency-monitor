package ru.otus.project.finance.financemonitorservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.otus.project.finance.financemonitorservice.dto.CurrencyDto;
import ru.otus.project.finance.financemonitorservice.properties.ConnectionProperties;
import ru.otus.project.finance.financemonitorservice.util.Constant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class DataRetrieveService {

    @Qualifier("coinMarketCapRestTemplate")
    RestTemplate coinMarketCapRestTemplate;
    @Qualifier("fixerRestTemplate")
    RestTemplate fixerRestTemplate;
    @Qualifier("polygonRestTemplate")
    RestTemplate polygonRestTemplate;
    ConnectionProperties connectionProperties;

    public JSONObject getMainCurrencies() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        Map<String, String> params = new HashMap<>();
        params.put(Constant.TOKEN, connectionProperties.getCoinMarketCapToken());

        ResponseEntity<String> responseEntity = coinMarketCapRestTemplate.exchange(connectionProperties.getListingsUrl(), HttpMethod.GET, httpEntity, String.class, params);
        return new JSONObject(responseEntity.getBody());
    }

    public JSONObject getStock() {
        LocalDate previousDay = LocalDate.now().minusDays(2);
        // Format the date as "YYYY-MM-DD"
        String formattedDate = previousDay.format(DateTimeFormatter.ISO_DATE);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        Map<String, String> params = new HashMap<>();
        params.put(Constant.TOKEN, connectionProperties.getPolygonToken());
        params.put(Constant.DATE, String.valueOf(previousDay));


        ResponseEntity<String> responseEntity = polygonRestTemplate.exchange(connectionProperties.getGetPolygonOnDateUrl(), HttpMethod.GET, httpEntity, String.class, params);
        return new JSONObject(responseEntity.getBody());
    }

    public JSONObject getSingleCryptoCurrency(String symbol) {
        // CoinMarketCap API URL
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        Map<String, String> params = new HashMap<>();
        params.put(Constant.TOKEN, connectionProperties.getCoinMarketCapToken());
        params.put(Constant.SYMBOL, symbol);

        ResponseEntity<String> responseEntity = coinMarketCapRestTemplate.exchange(connectionProperties.getQuotesUrl(), HttpMethod.GET, httpEntity, String.class, params);
        return new JSONObject(responseEntity.getBody());
    }

    public ResponseEntity<CurrencyDto> getCurrentCurrencies() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        Map<String, String> params = new HashMap<>();
        params.put(Constant.TOKEN, connectionProperties.getFixerToken());


        return fixerRestTemplate.exchange(connectionProperties.getLatestCurrenciesUrl(), HttpMethod.GET, httpEntity, CurrencyDto.class, params);
    }
}
