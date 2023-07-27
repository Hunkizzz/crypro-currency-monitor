-- liquibase formatted sql
CREATE EXTENSION IF NOT EXISTS "pgcrypto";
-- changeset postgres:1690407157152-1
CREATE TABLE currency (id UUID DEFAULT gen_random_uuid() NOT NULL, name VARCHAR(255), description VARCHAR(255), CONSTRAINT "currencyPK" PRIMARY KEY (id));

