package ru.otus.project.finance.financemonitorservice.controller.coinmarketcap;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.finance.financemonitorservice.dto.ValueDto;
import ru.otus.project.finance.financemonitorservice.service.coimarketcap.CryptoService;

@RestController
@RequestMapping("/api")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CryptoCurrencyController {
    CryptoService cryptoService;

    @GetMapping("/crypto/{symbol}")
    public ValueDto getHistoryCryptoCurrenciesByName(@PathVariable String symbol) {
        return cryptoService.getCryptoCurrencyBySymbol(symbol);
    }
}
