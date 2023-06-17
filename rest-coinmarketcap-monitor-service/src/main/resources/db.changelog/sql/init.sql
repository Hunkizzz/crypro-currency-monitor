-- liquibase formatted sql

-- changeset hunkz:1687022548322-1
CREATE TABLE CRYPTO_CURRENCY (uuid UUID NOT NULL, name VARCHAR(255), price FLOAT(53) NOT NULL, symbol VARCHAR(20), CONSTRAINT "crypto_currencyPK" PRIMARY KEY (uuid));

