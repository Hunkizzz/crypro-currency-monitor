-- liquibase formatted sql

-- changeset postgres:1687026426045-1
CREATE TABLE crypto (id UUID NOT NULL, name VARCHAR(255), symbol VARCHAR(255), CONSTRAINT "cryptoPK" PRIMARY KEY (id));

-- changeset postgres:1687026426045-2
CREATE TABLE crypto_currency (id UUID NOT NULL, price DECIMAL(38, 10), timestamp TIMESTAMP(6) WITHOUT TIME ZONE, crypto_id UUID, CONSTRAINT "crypto_currencyPK" PRIMARY KEY (id));

-- changeset postgres:1687026426045-3
ALTER TABLE crypto_currency ADD CONSTRAINT "CRYPTO_FK" FOREIGN KEY (crypto_id) REFERENCES crypto (id);

