package ru.otus.project.finance.financemonitorservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.finance.financemonitorservice.domain.Stock;

import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, UUID> {
}
