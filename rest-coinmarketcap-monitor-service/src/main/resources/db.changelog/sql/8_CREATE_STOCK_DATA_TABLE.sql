CREATE TABLE stock_data (
    id UUID DEFAULT gen_random_uuid() NOT NULL,
    symbol VARCHAR(255) NOT NULL,
    volume BIGINT NOT NULL,
    volume_weighted_average DOUBLE PRECISION NOT NULL,
    opening_price DOUBLE PRECISION NOT NULL,
    closing_price DOUBLE PRECISION NOT NULL,
    highest_price DOUBLE PRECISION NOT NULL,
    lowest_price DOUBLE PRECISION NOT NULL,
    timestamp BIGINT NOT NULL,
    number_of_transactions INTEGER NOT NULL,
    CONSTRAINT "stock_dataPK" PRIMARY KEY (id)
);