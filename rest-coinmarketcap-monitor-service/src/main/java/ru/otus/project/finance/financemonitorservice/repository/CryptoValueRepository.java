package ru.otus.project.finance.financemonitorservice.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.project.finance.financemonitorservice.domain.CryptoValue;
import ru.otus.project.finance.financemonitorservice.model.CryptoInfo;

import java.util.List;
import java.util.UUID;

public interface CryptoValueRepository extends JpaRepository<CryptoValue, UUID> {
    @EntityGraph(value = "crypto_entity_graph")
    List<CryptoValue> findAll();

    @Query(value = "SELECT " +
            "new ru.otus.project.finance.financemonitorservice.model.CryptoInfo " +
            "(crypto.name, crypto_currency.price) " +
            "FROM ru.otus.project.finance.financemonitorservice.domain.CryptoValue crypto_currency " +
            "JOIN ru.otus.project.finance.financemonitorservice.domain.Crypto crypto ON " +
            "crypto_currency.crypto.id = crypto.id  " +
            "WHERE crypto_currency.timestamp = (SELECT max(timestamp) " +
            "FROM ru.otus.project.finance.financemonitorservice.domain.CryptoValue)")
    List<CryptoInfo> findLastCurrencies();

    @Query(value = "SELECT " +
            "new ru.otus.project.finance.financemonitorservice.model.CryptoInfo " +
            "(crypto.name, crypto_currency.price, crypto_currency.timestamp) " +
            "FROM ru.otus.project.finance.financemonitorservice.domain.CryptoValue crypto_currency " +
            "JOIN ru.otus.project.finance.financemonitorservice.domain.Crypto crypto ON " +
            "crypto_currency.crypto.id = crypto.id  " +
            "WHERE crypto.name = :name")
    List<CryptoInfo> findAllByName(String name);
}
