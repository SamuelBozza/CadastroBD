# CadastroBD

# Foi usado o MySQL e MySQL Workbench para manipulação dos dados, no entanto usar o novo script para incersão das tabelas e dos dados(Arquivo com o nome: SQL NEW SCRIPT. Ou Usar o script abaixo).

# DIAGRAMA RELACIONAL

![diagrama relacional](https://github.com/SamuelBozza/CadastroBD/assets/102820398/77320e05-d4a5-4a65-af7c-c650196579ad)

```
# NOVO SCRIPT: 

-- SCRIPT DE CRIAÇÃO DAS TABELAS

CREATE TABLE Produto (
  idProduto INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(255),
  quantidade INT,
  precoVenda DECIMAL(12, 2)
);

CREATE TABLE Usuario (
  idUsuario INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100),
  senha VARCHAR(100)
);

CREATE TABLE Pessoa (
  idPessoa INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(255),
  logradouro VARCHAR(255),
  cidade VARCHAR(255),
  estado CHAR(2),
  telefone VARCHAR(11),
  email VARCHAR(255)
);

CREATE TABLE PessoaFisica (
  idPessoa INT,
  cpf VARCHAR(11) NOT NULL,
  PRIMARY KEY (idPessoa),
  FOREIGN KEY (idPessoa) REFERENCES Pessoa(idPessoa)
);

CREATE TABLE PessoaJuridica (
  idPessoa INT,
  cnpj VARCHAR(18) NOT NULL,
  PRIMARY KEY (idPessoa),
  FOREIGN KEY (idPessoa) REFERENCES Pessoa(idPessoa)
);

CREATE TABLE Movimento (
  idMovimento INT AUTO_INCREMENT PRIMARY KEY,
  Usuario_idUsuario INT NOT NULL,
  Pessoa_idPessoa INT NOT NULL,
  Produto_idProduto INT NOT NULL,
  quantidade INT,
  tipo CHAR(1),
  valorUnitario DECIMAL(12, 2),
  FOREIGN KEY (Produto_idProduto)
    REFERENCES Produto(idProduto),
  FOREIGN KEY (Pessoa_idPessoa)
    REFERENCES Pessoa(idPessoa),
  FOREIGN KEY (Usuario_idUsuario)
    REFERENCES Usuario(idUsuario)
);

CREATE INDEX Movimento_FKIndex1 ON Movimento (Produto_idProduto);
CREATE INDEX Movimento_FKIndex2 ON Movimento (Pessoa_idPessoa);
CREATE INDEX Movimento_FKIndex3 ON Movimento (Usuario_idUsuario);
CREATE INDEX IFK_ItemMovimentado ON Movimento (Produto_idProduto);
CREATE INDEX IFK_Responsavel ON Movimento (Pessoa_idPessoa);
CREATE INDEX IFK_User ON Movimento (Usuario_idUsuario);

-- SCRIPT DE INSERÇÃO NAS TABELAS

-- INSERINDO USUARIOS
-- ID de usuario gerado automaticamente

INSERT INTO usuario (nome, senha) VALUES ('op1', 'op1');
INSERT INTO usuario (nome, senha) VALUES ('op2', 'op2');

-- INSERINDO PRODUTOS
-- ID de produto gerado automaticamente

INSERT INTO produto (nome, quantidade, precoVenda) VALUES ('Banana', 100, 5.00);
INSERT INTO produto (nome, quantidade, precoVenda) VALUES ('Laranja', 500, 2.00);
INSERT INTO produto (nome, quantidade, precoVenda) VALUES ('Manga', 800, 4.00);

-- Inserindo PESSOA FISICA
-- ID de Pessoa gerado automaticamente

INSERT INTO pessoa (nome, logradouro, cidade, estado, telefone, email) VALUES ('Joao', 'Rua 12, casa 3, Quitanda', 'Riacho do Sul', 'PA', '11111111111', 'joao@riacho.com');

INSERT INTO pessoaFisica (idPessoa, cpf) VALUES (1, '11111111111');

-- Inserindo PESSOA JURIDICA
-- ID de Pessoa gerado automaticamente

INSERT INTO pessoa (nome, logradouro, cidade, estado, telefone, email) VALUES ('JJC', 'Rua 11, Centro', 'Riacho do Norte', 'PA', '22222222222', 'jjc@riacho.com');

INSERT INTO pessoaJuridica (idPessoa, cnpj) VALUES (2, '111111111111111111');

-- Inserindo MOVIMENTAÇÕES
-- ID de Movimentações gerado automaticamente

INSERT INTO movimento (Usuario_idUsuario, Pessoa_idPessoa, Produto_idProduto, quantidade, tipo, valorUnitario) VALUES (1, 1, 1, 20, 'S', 4.00);
INSERT INTO movimento (Usuario_idUsuario, Pessoa_idPessoa, Produto_idProduto, quantidade, tipo, valorUnitario) VALUES (1, 1, 2, 15, 'S', 2.00);
INSERT INTO movimento (Usuario_idUsuario, Pessoa_idPessoa, Produto_idProduto, quantidade, tipo, valorUnitario) VALUES (2, 1, 2, 10, 'S', 3.00);
INSERT INTO movimento (Usuario_idUsuario, Pessoa_idPessoa, Produto_idProduto, quantidade, tipo, valorUnitario) VALUES (1, 2, 2, 15, 'E', 5.00);
INSERT INTO movimento (Usuario_idUsuario, Pessoa_idPessoa, Produto_idProduto, quantidade, tipo, valorUnitario) VALUES (1, 3, 3, 20, 'E', 4.00);
```
