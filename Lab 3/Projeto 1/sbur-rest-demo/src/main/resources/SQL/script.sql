CREATE TABLE IF NOT EXISTS Cafe (
    id INT(3) CONSTRAINT pk_id primary key NOT NULL,
    nome VARCHAR(20) NOT NULL,
    CONSTRAINT uk_nome UNIQUE(nome)
);

INSERT INTO Cafe (nome) VALUES (

                        )

