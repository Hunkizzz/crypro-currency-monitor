package ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.model.CryptoValue;

import java.util.List;
import java.util.UUID;

public interface CryptoValueRepository extends JpaRepository<CryptoValue, UUID> {
    @EntityGraph(value = "crypto_entity_graph")
    List<CryptoValue> findAll();
}
