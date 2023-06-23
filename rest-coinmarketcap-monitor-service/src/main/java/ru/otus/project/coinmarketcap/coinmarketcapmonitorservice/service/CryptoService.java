package ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.dto.ValueDto;
import ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.repository.CryptoRepository;
import ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.repository.CryptoValueRepository;
import static ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.util.Constant.DATA;
import static ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.util.Constant.QUOTE;
import static ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.util.Constant.USD;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CryptoService {
    CryptoValueRepository cryptoValueRepository;
    CryptoRepository cryptoRepository;
    DataRetrieveService dataRetrieveService;
    ObjectMapper objectMapper;

    public ValueDto getCryptoCurrencyBySymbol(String symbol) {
        JSONObject jsonObject = dataRetrieveService.getSingleCryptoCurrency(symbol);
        JSONObject data = jsonObject.getJSONObject(DATA);
        JSONObject currency = data.getJSONObject(symbol.toUpperCase());
        JSONObject quote = currency.getJSONObject(QUOTE);
        JSONObject usd = quote.getJSONObject(USD);
        ValueDto valueDto;
        try {
            valueDto = objectMapper.readValue(usd.toString(), ValueDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        valueDto.setSymbol(symbol);
        return valueDto;
    }
}
