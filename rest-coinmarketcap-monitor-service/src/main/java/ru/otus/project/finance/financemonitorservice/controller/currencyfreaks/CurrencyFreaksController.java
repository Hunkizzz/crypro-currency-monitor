package ru.otus.project.finance.financemonitorservice.controller.currencyfreaks;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.finance.financemonitorservice.dto.CurrencyDto;
import ru.otus.project.finance.financemonitorservice.service.fixer.CurrencyService;

@RestController
@RequestMapping("/api")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CurrencyFreaksController {
    CurrencyService currencyService;

    @GetMapping("/currency")
    public ResponseEntity<CurrencyDto> getCurrencies() {
        return currencyService.getCurrentCurrencies();
    }
}