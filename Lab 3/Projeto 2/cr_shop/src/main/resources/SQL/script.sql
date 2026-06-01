CREATE TABLE Amigurumi (
    am_id INTEGER CONSTRAINT PK_Amigurumi PRIMARY KEY,
    am_nome TEXT NOT NULL,
    am_foto TEXT UNIQUE,
    am_preco MONEY NOT NULL
);

CREATE TABLE Cor (
    cor_codigo TEXT CONSTRAINT PK_Cor PRIMARY KEY,
    cor_nome TEXT NOT NULL,
    cor_foto TEXT UNIQUE
);

CREATE TABLE Acessorio (
    ac_id INTEGER CONSTRAINT PK_Acessorio PRIMARY KEY,
    ac_nome TEXT NOT NULL,
    ac_foto TEXT UNIQUE,
    ac_preco MONEY NOT NULL
);

CREATE TABLE Customizacao (
    cust_id   INTEGER CONSTRAINT PK_Customizacao PRIMARY KEY,
    am_id     INTEGER NOT NULL,
    cust_nome TEXT    NOT NULL,
    CONSTRAINT FK_Customizacao_Amigurumi
        FOREIGN KEY (am_id) REFERENCES Amigurumi(am_id)
        ON DELETE CASCADE
);

CREATE TABLE Opcao (
   op_id     INTEGER CONSTRAINT PK_Opcao PRIMARY KEY,
   cust_id   INTEGER NOT NULL,
   op_nome   TEXT    NOT NULL,
   CONSTRAINT FK_Opcao_Customizacao
       FOREIGN KEY (cust_id) REFERENCES Customizacao(cust_id)
           ON DELETE CASCADE
);

CREATE TABLE Usuario (
    us_id INTEGER CONSTRAINT PK_Usuario PRIMARY KEY,
    us_nome TEXT NOT NULL,
    us_senha TEXT NOT NULL,
    us_email TEXT UNIQUE
);

CREATE TABLE Compra (
    com_id INTEGER CONSTRAINT PK_Compra PRIMARY KEY,
    us_id INTEGER,
    com_data TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT FK_Compra_Usuario
        FOREIGN KEY(us_id)
            REFERENCES Usuario(us_id)
            ON DELETE CASCADE
);

CREATE TABLE Item (
    it_id INTEGER CONSTRAINT PK_Item PRIMARY KEY,
    am_id INTEGER,
    com_id INTEGER,
    it_quantidade INTEGER NOT NULL DEFAULT 1,
    it_preco MONEY NOT NULL,
    CONSTRAINT FK_Item_Amigurumi
      FOREIGN KEY(am_id)
          REFERENCES Amigurumi(am_id)
          ON DELETE CASCADE,
    CONSTRAINT FK_Item_Compra
      FOREIGN KEY(com_id)
          REFERENCES Compra(com_id)
          ON DELETE CASCADE
);

CREATE TABLE AmCor (
    am_id INTEGER,
    cor_codigo TEXT,
    CONSTRAINT PK_AmCor
       PRIMARY KEY(am_id, cor_codigo),
    CONSTRAINT FK_AmCor_Amigurumi
       FOREIGN KEY(am_id)
           REFERENCES Amigurumi(am_id)
           ON DELETE CASCADE,
    CONSTRAINT FK_AmCor_Cor
       FOREIGN KEY(cor_codigo)
           REFERENCES Cor(cor_codigo)
           ON DELETE CASCADE
);

CREATE TABLE CorAc (
    cor_codigo TEXT,
    ac_id INTEGER,
    CONSTRAINT PK_CorAc
       PRIMARY KEY(cor_codigo, ac_id),
    CONSTRAINT FK_CorAc_Cor
       FOREIGN KEY(cor_codigo)
           REFERENCES Cor(cor_codigo)
           ON DELETE CASCADE,
    CONSTRAINT FK_CorAc_Acessorio
       FOREIGN KEY(ac_id)
           REFERENCES Acessorio(ac_id)
           ON DELETE CASCADE
);

CREATE TABLE AmAc (
    am_id INTEGER,
    ac_id INTEGER,
    CONSTRAINT PK_AmAc PRIMARY KEY(am_id, ac_id),
    CONSTRAINT FK_AmAc_Amigurumi
        FOREIGN KEY(am_id)
            REFERENCES Amigurumi(am_id)
            ON DELETE CASCADE,
    CONSTRAINT FK_AmAc_Acessorio
        FOREIGN KEY(ac_id)
            REFERENCES Acessorio(ac_id)
            ON DELETE CASCADE
);

CREATE TABLE ItAcCor (
    it_id      INTEGER,
    ac_id      INTEGER,
    cor_codigo TEXT    NOT NULL,
    CONSTRAINT PK_ItAcCor PRIMARY KEY (it_id, ac_id),
    CONSTRAINT FK_ItAcCor_Item
        FOREIGN KEY (it_id) REFERENCES Item(it_id)
        ON DELETE CASCADE,
    CONSTRAINT FK_ItAcCor_Acessorio
        FOREIGN KEY (ac_id) REFERENCES Acessorio(ac_id)
        ON DELETE CASCADE,
    CONSTRAINT FK_ItAcCor_Cor
        FOREIGN KEY (cor_codigo) REFERENCES Cor(cor_codigo)
        ON DELETE CASCADE
);

CREATE TABLE ItCust (
    it_id INTEGER,
    op_id INTEGER,
    CONSTRAINT PK_ItCust PRIMARY KEY (it_id, op_id),
    CONSTRAINT FK_ItCust_Item
        FOREIGN KEY (it_id) REFERENCES Item(it_id)
        ON DELETE CASCADE,
    CONSTRAINT FK_ItCust_Opcao
        FOREIGN KEY (op_id) REFERENCES Opcao(op_id)
        ON DELETE CASCADE
);