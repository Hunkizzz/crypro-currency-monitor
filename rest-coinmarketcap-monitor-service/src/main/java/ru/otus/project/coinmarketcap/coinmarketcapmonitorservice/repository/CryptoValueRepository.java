package ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.model.CryptoValue;

import java.util.UUID;

public interface CryptoValueRepository extends JpaRepository<CryptoValue, UUID> {
}
