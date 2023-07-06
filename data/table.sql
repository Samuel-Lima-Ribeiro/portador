CREATE TABLE IF NOT EXISTS PORTADOR (
    id_portador uuid NOT NULL,
    status VARCHAR(12),
    limite NUMERIC(10,2),
    cliente_id uuid unique,
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

CREATE TABLE IF NOT EXISTS CARTAO (
    id uuid NOT NULL,
    limite NUMERIC(10,2),
    card_number varchar(50),
    cvv varchar(100),
    due_date date,
    portador_id uuid,
    created_at timestamp,
    updated_at timestamp,
    PRIMARY KEY(id)
);

ALTER TABLE CARTAO
ADD CONSTRAINT fk_portador_id
FOREIGN KEY(portador_id) REFERENCES PORTADOR (id_portador);