package ru.otus.project.finance.financemonitorservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.finance.financemonitorservice.domain.Crypto;

import java.util.UUID;

public interface CryptoRepository extends JpaRepository<Crypto, UUID> {

    Crypto findBySymbol(String symbol);
}
