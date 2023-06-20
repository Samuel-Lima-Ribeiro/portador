CREATE TABLE IF NOT EXISTS PORTADOR (
    id uuid NOT NULL,
    status VARCHAR(8),
    limite NUMERIC(10,2),
    created_at TIMESTAMP,
    PRIMARY KEY(id)
);