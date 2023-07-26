package ru.otus.project.finance.financemonitorservice.schedule;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.otus.project.finance.financemonitorservice.service.coimarketcap.HistoryCryptoService;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CryptoUpdate {
    HistoryCryptoService historyCryptoService;

    @Scheduled(fixedDelay = 1000 * 60 * 30)
    public void scheduleUpdateCryptoCurrency() {
        historyCryptoService.saveCryptoCurrency();
    }
}
