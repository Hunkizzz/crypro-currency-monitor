package ru.otus.project.finance.financemonitorservice.service.fixer;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.otus.project.finance.financemonitorservice.dto.CurrencyDto;
import ru.otus.project.finance.financemonitorservice.service.DataRetrieveService;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CurrencyService {
    DataRetrieveService dataRetrieveService;

    public ResponseEntity<CurrencyDto> getCurrentCurrencies() {
        return dataRetrieveService.getCurrentCurrencies();
    }
}