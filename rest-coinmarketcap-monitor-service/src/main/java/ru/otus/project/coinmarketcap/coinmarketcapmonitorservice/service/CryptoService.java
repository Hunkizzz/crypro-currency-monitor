package ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.service;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.model.Crypto;
import ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.model.CryptoValue;
import ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.repository.CryptoRepository;
import ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.repository.CryptoValueRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CryptoService {
    final CryptoValueRepository cryptoValueRepository;
    final CryptoRepository cryptoRepository;
    final Map<String, Crypto> savedCrypto = new HashMap<String, Crypto>();

    private static final String SYMBOL = "symbol";
    private static final String DATA = "data";
    private static final String QUOTE = "quote";
    private static final String USD = "USD";
    private static final String SLUG = "slug";
    private static final String PRICE = "price";

    @Value("${coinmarketcap.token}")
    String token;

    @PostConstruct
    public void init() {
        cryptoRepository.findAll().forEach(crypto -> {
            savedCrypto.put(crypto.getSymbol(), crypto);
        });
    }

    public void saveCryptoCurrency() {
        try {
            URL url = new URL("https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?CMC_PRO_API_KEY=" + token);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            StringBuilder sb = new StringBuilder();
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            br.close();
            conn.disconnect();

            JSONObject json = new JSONObject(sb.toString());
            JSONArray jsonArray = json.getJSONArray(DATA);

            for (int i = 0, size = jsonArray.length(); i < size; i++) {
                JSONObject objectInArray = jsonArray.getJSONObject(i);
                JSONObject jsonQuote = objectInArray.getJSONObject(QUOTE);
                JSONObject jsonUsd = jsonQuote.getJSONObject("USD");
                String symbol = objectInArray.getString(SYMBOL);
                if (!savedCrypto.containsKey(objectInArray.getString(SYMBOL))) {
                    Crypto crypto = new Crypto();
                    crypto.setName(objectInArray.getString(SLUG));
                    crypto.setSymbol(symbol);
                    cryptoRepository.save(crypto);
                    crypto = cryptoRepository.findBySymbol(symbol);
                    savedCrypto.put(symbol, crypto);
                }
                CryptoValue cryptoValue = new CryptoValue();
                cryptoValue.setCrypto(savedCrypto.get(symbol));
                cryptoValue.setTimeStamp(LocalDateTime.now());
                cryptoValue.setPrice(jsonUsd.getBigDecimal(PRICE));
                cryptoValueRepository.save(cryptoValue);
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
