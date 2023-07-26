package ru.otus.project.finance.financemonitorservice.service.coimarketcap;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import ru.otus.project.finance.financemonitorservice.domain.Crypto;
import ru.otus.project.finance.financemonitorservice.domain.CryptoValue;
import ru.otus.project.finance.financemonitorservice.dto.CryptoDto;
import ru.otus.project.finance.financemonitorservice.repository.CryptoRepository;
import ru.otus.project.finance.financemonitorservice.repository.CryptoValueRepository;
import ru.otus.project.finance.financemonitorservice.service.DataRetrieveService;
import ru.otus.project.finance.financemonitorservice.util.Constant;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class HistoryCryptoService {
    CryptoRepository cryptoRepository;
    DataRetrieveService dataRetrieveService;
    CryptoValueRepository cryptoValueRepository;
    Map<String, Crypto> savedCrypto = new HashMap<String, Crypto>();

    @PostConstruct
    public void init() {
        cryptoRepository.findAll().forEach(crypto -> {
            savedCrypto.put(crypto.getSymbol(), crypto);
        });
    }

    public void saveCryptoCurrency() {
        JSONObject jsonObject = dataRetrieveService.getMainCurrencies();
        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
        LocalDateTime dateTime = LocalDateTime.now();
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            JSONObject objectInArray = jsonArray.getJSONObject(i);
            JSONObject jsonQuote = objectInArray.getJSONObject(Constant.QUOTE);
            JSONObject jsonUsd = jsonQuote.getJSONObject(Constant.USD);
            String symbol = objectInArray.getString(Constant.SYMBOL);
            if (!savedCrypto.containsKey(objectInArray.getString(Constant.SYMBOL))) {
                Crypto crypto = new Crypto();
                crypto.setName(objectInArray.getString(Constant.SLUG));
                crypto.setSymbol(symbol);
                cryptoRepository.save(crypto);
                crypto = cryptoRepository.findBySymbol(symbol);
                savedCrypto.put(symbol, crypto);
            }
            CryptoValue cryptoValue = new CryptoValue();
            cryptoValue.setCrypto(savedCrypto.get(symbol));
            cryptoValue.setTimestamp(dateTime);
            cryptoValue.setPrice(jsonUsd.getBigDecimal(Constant.PRICE));
            cryptoValueRepository.save(cryptoValue);
        }
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

    public List<CryptoDto> getHistoryCryptoCurrenciesByName(String name) {
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
}
