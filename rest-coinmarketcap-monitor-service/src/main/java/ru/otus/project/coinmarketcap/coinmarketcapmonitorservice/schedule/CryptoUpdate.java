package ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.schedule;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.service.CryptoService;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CryptoUpdate {
    CryptoService cryptoService;
    @Scheduled(fixedDelay = 1000 * 60 * 15)
    public void scheduleUpdateCryptoCurrency() {
        cryptoService.saveCryptoCurrency();
    }
}
