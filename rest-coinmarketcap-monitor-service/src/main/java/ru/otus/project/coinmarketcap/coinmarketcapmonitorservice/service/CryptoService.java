package ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.service;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.domain.Crypto;
import ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.domain.CryptoValue;
import ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.dto.CryptoDto;
import ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.repository.CryptoRepository;
import ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.repository.CryptoValueRepository;
import static ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.util.Constant.DATA;
import static ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.util.Constant.PRICE;
import static ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.util.Constant.QUOTE;
import static ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.util.Constant.SLUG;
import static ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.util.Constant.SYMBOL;
import static ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.util.Constant.USD;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CryptoService {
    CryptoValueRepository cryptoValueRepository;
    CryptoRepository cryptoRepository;
    DataRetrieveService dataRetrieveService;
    Map<String, Crypto> savedCrypto = new HashMap<String, Crypto>();

    @PostConstruct
    public void init() {
        cryptoRepository.findAll().forEach(crypto -> {
            savedCrypto.put(crypto.getSymbol(), crypto);
        });
    }

    public void saveCryptoCurrency() {
        JSONObject jsonObject = dataRetrieveService.getMainCurrencies();
        JSONArray jsonArray = jsonObject.getJSONArray(DATA);
        LocalDateTime dateTime = LocalDateTime.now();
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            JSONObject objectInArray = jsonArray.getJSONObject(i);
            JSONObject jsonQuote = objectInArray.getJSONObject(QUOTE);
            JSONObject jsonUsd = jsonQuote.getJSONObject(USD);
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
            cryptoValue.setTimestamp(dateTime);
            cryptoValue.setPrice(jsonUsd.getBigDecimal(PRICE));
            cryptoValueRepository.save(cryptoValue);
        }

    }

    public List<CryptoDto> getCryptoCurrencies() {
        List<CryptoDto> cryptoDtos = new ArrayList<>();
        cryptoValueRepository.findAll().forEach(cryptoValue -> {
            CryptoDto cryptoDto = CryptoDto.builder()
                    .name(cryptoValue.getCrypto().getName())
                    .price(cryptoValue.getPrice())
                    .insertDate(cryptoValue.getTimestamp())
                    .build();
            cryptoDtos.add(cryptoDto);
        });
        return cryptoDtos;
    }

    public List<CryptoDto> getLastCryptoCurrencies() {
        List<CryptoDto> cryptoDtos = new ArrayList<>();
        cryptoValueRepository.findLastCurrencies().forEach(cryptoValue -> {
            CryptoDto cryptoDto = CryptoDto.builder()
                    .name(cryptoValue.getName())
                    .price(cryptoValue.getPrice())
                    .insertDate(cryptoValue.getTimestamp())
                    .build();
            cryptoDtos.add(cryptoDto);
        });
        return cryptoDtos;
    }

    public List<CryptoDto> getCryptoCurrenciesByName(String name) {
        List<CryptoDto> cryptoDtos = new ArrayList<>();
        cryptoValueRepository.findAllByName(name).forEach(cryptoValue -> {
            CryptoDto cryptoDto = CryptoDto.builder()
                    .name(cryptoValue.getName())
                    .price(cryptoValue.getPrice())
                    .insertDate(cryptoValue.getTimestamp())
                    .build();
            cryptoDtos.add(cryptoDto);
        });
        return cryptoDtos;
    }
}
