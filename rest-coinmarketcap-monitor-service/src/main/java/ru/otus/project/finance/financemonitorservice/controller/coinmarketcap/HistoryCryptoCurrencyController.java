package ru.otus.project.finance.financemonitorservice.controller.coinmarketcap;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.finance.financemonitorservice.dto.CryptoDto;
import ru.otus.project.finance.financemonitorservice.service.coimarketcap.HistoryCryptoService;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
//@CrossOrigin("*")
public class HistoryCryptoCurrencyController {
    HistoryCryptoService historyCryptoService;

    @GetMapping("/")
    public ResponseEntity<?> saveCryptoCurrency() {
        historyCryptoService.saveCryptoCurrency();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/crypto")
    public List<CryptoDto> getCurrencies() {
        return historyCryptoService.getCryptoCurrencies();
    }

    @GetMapping("/crypto_currency")
    public List<CryptoDto> getLastCurrencies() {
        return historyCryptoService.getLastCryptoCurrencies();
    }

    @GetMapping("/crypto/{name}")
    public List<CryptoDto> getHistoryCryptoCurrenciesByName(@PathVariable String name) {
        return historyCryptoService.getHistoryCryptoCurrenciesByName(name);
    }

}
