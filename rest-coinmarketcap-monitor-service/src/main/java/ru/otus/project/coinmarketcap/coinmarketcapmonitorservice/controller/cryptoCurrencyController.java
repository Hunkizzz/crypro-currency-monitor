package ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.dto.CryptoDto;
import ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.service.CryptoService;

import java.util.List;

@RestController
@RequestMapping("/api")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@CrossOrigin("*")
public class cryptoCurrencyController {
    CryptoService cryptoService;

    @GetMapping("/")
    public ResponseEntity<?> createComment() {
        cryptoService.saveCryptoCurrency();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/crypto")
    public List<CryptoDto> getCurrencies() {
        return cryptoService.getCryptoCurrencies();
    }
    @GetMapping("/current_crypto_currency")
    public List<CryptoDto> getLastCurrencies() {
        return cryptoService.getLastCryptoCurrencies();
    }
    @GetMapping("/crypto/{name}")
    public List<CryptoDto> getCryptoCurrenciesByName(@PathVariable String name) {
        return cryptoService.getCryptoCurrenciesByName(name);
    }

}
