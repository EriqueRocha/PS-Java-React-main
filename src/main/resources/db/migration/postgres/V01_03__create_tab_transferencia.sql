CREATE TABLE IF NOT EXISTS transferencia (
    id SERIAL PRIMARY KEY,
    data_transferencia DATE,
    valor FLOAT,
    tipo VARCHAR(255),
    nome_operador_transacao VARCHAR(255),
    conta_id INTEGER NOT NULL,
    FOREIGN KEY (conta_id) REFERENCES conta (id_conta)
);