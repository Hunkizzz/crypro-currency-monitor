package ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.model.Crypto;

import java.util.UUID;

public interface CryptoRepository extends JpaRepository<Crypto, UUID> {

    Crypto findBySymbol(String symbol);
}
