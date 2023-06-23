package ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.dto.ValueDto;
import ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.service.CryptoService;

@RestController
@RequestMapping("/api")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@CrossOrigin("*")
public class CryptoCurrencyController {
    CryptoService cryptoService;

    @GetMapping("/crypto/{symbol}")
    public ValueDto getHistoryCryptoCurrenciesByName(@PathVariable String symbol) {
        return cryptoService.getCryptoCurrencyBySymbol(symbol);
    }
}
