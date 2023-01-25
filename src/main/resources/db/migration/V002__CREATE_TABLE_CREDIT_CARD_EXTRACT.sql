CREATE TABLE IF NOT EXISTS CREDIT_CARD_EXTRACT (
    id BIGSERIAL NOT NULL,
    date_credit DATE NOT NULL,
    description TEXT NOT NULL,
    value_credit NUMERIC(20, 2) NOT NULL,
    CONSTRAINT PK_CREDIT_CARD_EXTRACT PRIMARY KEY (id)
);

CREATE SEQUENCE CREDIT_CARD_EXTRACT_SEQ START WITH 1 INCREMENT BY 1;