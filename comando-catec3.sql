CREATE TABLE alunos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    instituicao VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    data_nascimento DATE
);

CREATE TABLE professores (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    instituicao VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    data_nascimento DATE,
    area_atuacao VARCHAR(255)
);

CREATE TABLE textos (
	id SERIAL PRIMARY KEY, 
	conteudo TEXT
);

ALTER TABLE textos ADD COLUMN author_id INT;
ALTER TABLE textos ADD COLUMN author_type VARCHAR(10);