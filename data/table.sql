CREATE TABLE IF NOT EXISTS PORTADOR (
    id_portador uuid NOT NULL,
    status VARCHAR(8),
    limite NUMERIC(10,2),
--    cliente_id uuid unique,
    cliente_id uuid,
    analise_credito_id uuid,
    conta_bancaria_id uuid,
    created_at timestamp,
    updated_at timestamp,
    PRIMARY KEY(id_portador)
);

CREATE TABLE IF NOT EXISTS CONTA_BANCARIA (
    id uuid NOT NULL,
    account varchar(20),
    agency varchar(20),
    bank_code varchar(20),
    created_at timestamp,
    updated_at timestamp,
    PRIMARY KEY(id)
);

ALTER TABLE portador
ADD CONSTRAINT fk_conta_bancaria_id
FOREIGN KEY(conta_bancaria_id) REFERENCES CONTA_BANCARIA (id);