
CREATE TABLE nasdaq (id UUID DEFAULT gen_random_uuid() NOT NULL,
symbol	VARCHAR(512),
name	VARCHAR(512),
security_name	VARCHAR(512),
market_category	VARCHAR(512),
test_issue	VARCHAR(512),
financial_status	VARCHAR(512),
round_lot_size	NUMERIC(4, 1),
CONSTRAINT "nasdaqPK" PRIMARY KEY (id));

