package ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.properties.ConnectionProperties;
import static ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.util.Constant.SYMBOL;
import static ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.util.Constant.TOKEN;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class DataRetrieveService {
    RestTemplate restTemplate;
    ConnectionProperties connectionProperties;

    public JSONObject getMainCurrencies() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        Map<String, String> params = new HashMap<>();
        params.put(TOKEN, connectionProperties.getToken());

        ResponseEntity<String> responseEntity = restTemplate.exchange(connectionProperties.getListingsUrl()
                , HttpMethod.GET
                , httpEntity
                , String.class
                , params);
        return new JSONObject(responseEntity.getBody());
    }

    public JSONObject getSingleCryptoCurrency(String symbol) {
        // CoinMarketCap API URL
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        Map<String, String> params = new HashMap<>();
        params.put(TOKEN, connectionProperties.getToken());
        params.put(SYMBOL, symbol);

        ResponseEntity<String> responseEntity = restTemplate.exchange(connectionProperties.getQuotesUrl()
                , HttpMethod.GET
                , httpEntity
                , String.class
                , params);
        return new JSONObject(responseEntity.getBody());
    }
}
