DROP TABLE Cafe;

CREATE TABLE IF NOT EXISTS Cafe (
    id INTEGER CONSTRAINT pk_id primary key NOT NULL,
    nome VARCHAR(20) NOT NULL,
    CONSTRAINT uk_nome UNIQUE(nome)
);
/*Popular tabela*/
INSERT INTO Cafe (nome) VALUES ('capuccino');

INSERT INTO Cafe (nome) VALUES ('americano');

/*Overview*/
SELECT * FROM Cafe;

SELECT * FROM Cafe WHERE id = 1;

DELETE FROM Cafe WHERE id = 1;
