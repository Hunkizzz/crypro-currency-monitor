package ru.otus.project.finance.financemonitorservice.service.coimarketcap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import ru.otus.project.finance.financemonitorservice.dto.ValueDto;
import ru.otus.project.finance.financemonitorservice.repository.CryptoRepository;
import ru.otus.project.finance.financemonitorservice.repository.CryptoValueRepository;
import ru.otus.project.finance.financemonitorservice.service.DataRetrieveService;
import ru.otus.project.finance.financemonitorservice.util.Constant;

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
        JSONObject data = jsonObject.getJSONObject(Constant.DATA);
        JSONObject currency = data.getJSONObject(symbol.toUpperCase());
        JSONObject quote = currency.getJSONObject(Constant.QUOTE);
        JSONObject usd = quote.getJSONObject(Constant.USD);
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
