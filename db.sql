-- Gravadora CD Records Worldwide INC.

--Entidades:
CREATE TABLE Criador(
                        id bigserial PRIMARY KEY,
                        nome varchar(255),
                        descricao varchar(255),
                        genero varchar(255)
);
CREATE TABLE Musico(
                       nroDeRegistro bigserial PRIMARY KEY REFERENCES Criador(id),
                       cep      varchar(8),
                       rua      varchar(255),
                       cidade   varchar(255),
                       estado   varchar(255),
                       telefone varchar(25)
)INHERITS (Criador);
CREATE TABLE Banda(
                      id bigserial PRIMARY KEY REFERENCES Criador(id),
                      dataDeFormacao date
)INHERITS (Criador);

CREATE TABLE Disco(
                      identificador bigserial PRIMARY KEY,
                      dataLancamento date,
                      preco     real,
                      platinas  int,
                      titulo    varchar(255),
                      formato   varchar(255),
                      descricao varchar(255),
                      genero    varchar(255)
);
CREATE TABLE Produtor(
                         id bigserial PRIMARY KEY,
                         nome      varchar(255),
                         biografia varchar(255)
);
CREATE TABLE Instrumento(
                            codigoInterno bigserial PRIMARY KEY,
                            marca varchar(255),
                            tipo  varchar(255),
                            nome  varchar(255)
);
CREATE TABLE Musica(
                       id bigserial PRIMARY KEY,
                       duracao real,
                       faixa   int,
                       autores varchar(255),
                       titulo  varchar(255),
                       letra   text
);

--Relacionamentos:
CREATE TABLE Produzir(
                         id         bigserial PRIMARY KEY,
                         idDisco    bigint,
                         idProdutor bigint,
                         FOREIGN KEY (idDisco) REFERENCES Disco(identificador),
                         FOREIGN KEY (idProdutor) REFERENCES Produtor(id)
);
CREATE TABLE Lancamento(
                           id        bigserial PRIMARY KEY,
                           idDisco   bigint,
                           idCriador bigint,
                           FOREIGN KEY (idDisco) REFERENCES Disco(identificador),
                           FOREIGN KEY (idCriador) REFERENCES Criador(id)
);
CREATE TABLE Tocar(
                      id bigserial PRIMARY KEY,
                      idInstrumento bigint,
                      idMusico bigint,
                      FOREIGN KEY (idInstrumento) REFERENCES Instrumento(codigoInterno),
                      FOREIGN KEY (idMusico) REFERENCES Musico(nroDeRegistro)
);
CREATE TABLE Integrar(
                         id bigserial PRIMARY KEY,
                         idMusico bigint,
                         idBanda bigint,
                         FOREIGN KEY (idMusico) REFERENCES Musico(nroDeRegistro),
                         FOREIGN KEY (idBanda) REFERENCES Banda(id)
);
CREATE TABLE Participacao(
                             id bigserial PRIMARY KEY,
                             idMusica bigint,
                             idCriador bigint,
                             FOREIGN KEY (idMusica) REFERENCES Musica(id),
                             FOREIGN KEY (idCriador) REFERENCES Criador(id)
);
CREATE TABLE Incluir(
                        id bigserial PRIMARY KEY,
                        idMusica bigint,
                        idDisco bigint,
                        FOREIGN KEY (idMusica) REFERENCES Musica(id),
                        FOREIGN KEY (idDisco) REFERENCES Disco(identificador)
);-- Gravadora CD Records Worldwide INC.

--Entidades:
CREATE TABLE Criador(
                        id bigserial PRIMARY KEY,
                        nome varchar(255),
                        descricao varchar(255),
                        genero varchar(255)
);
CREATE TABLE Musico(
                       nroDeRegistro bigserial PRIMARY KEY REFERENCES Criador(id),
                       cep      varchar(8),
                       rua      varchar(255),
                       cidade   varchar(255),
                       estado   varchar(255),
                       telefone varchar(25)
)INHERITS (Criador);
CREATE TABLE Banda(
                      id bigserial PRIMARY KEY REFERENCES Criador(id),
                      dataDeFormacao date
)INHERITS (Criador);

CREATE TABLE Disco(
                      identificador bigserial PRIMARY KEY,
                      dataLancamento date,
                      preco     real,
                      platinas  int,
                      titulo    varchar(255),
                      formato   varchar(255),
                      descricao varchar(255),
                      genero    varchar(255)
);
CREATE TABLE Produtor(
                         id bigserial PRIMARY KEY,
                         nome      varchar(255),
                         biografia varchar(255)
);
CREATE TABLE Instrumento(
                            codigoInterno bigserial PRIMARY KEY,
                            marca varchar(255),
                            tipo  varchar(255),
                            nome  varchar(255)
);
CREATE TABLE Musica(
                       id bigserial PRIMARY KEY,
                       duracao real,
                       faixa   int,
                       autores varchar(255),
                       titulo  varchar(255),
                       letra   text
);

--Relacionamentos:
CREATE TABLE Produzir(
                         id         bigserial PRIMARY KEY,
                         idDisco    bigint,
                         idProdutor bigint,
                         FOREIGN KEY (idDisco) REFERENCES Disco(identificador),
                         FOREIGN KEY (idProdutor) REFERENCES Produtor(id)
);
CREATE TABLE Lancamento(
                           id        bigserial PRIMARY KEY,
                           idDisco   bigint,
                           idCriador bigint,
                           FOREIGN KEY (idDisco) REFERENCES Disco(identificador),
                           FOREIGN KEY (idCriador) REFERENCES Criador(id)
);
CREATE TABLE Tocar(
                      id bigserial PRIMARY KEY,
                      idInstrumento bigint,
                      idMusico bigint,
                      FOREIGN KEY (idInstrumento) REFERENCES Instrumento(codigoInterno),
                      FOREIGN KEY (idMusico) REFERENCES Musico(nroDeRegistro)
);
CREATE TABLE Integrar(
                         id bigserial PRIMARY KEY,
                         idMusico bigint,
                         idBanda bigint,
                         FOREIGN KEY (idMusico) REFERENCES Musico(nroDeRegistro),
                         FOREIGN KEY (idBanda) REFERENCES Banda(id)
);
CREATE TABLE Participacao(
                             id bigserial PRIMARY KEY,
                             idMusica bigint,
                             idCriador bigint,
                             FOREIGN KEY (idMusica) REFERENCES Musica(id),
                             FOREIGN KEY (idCriador) REFERENCES Criador(id)
);
CREATE TABLE Incluir(
                        id bigserial PRIMARY KEY,
                        idMusica bigint,
                        idDisco bigint,
                        FOREIGN KEY (idMusica) REFERENCES Musica(id),
                        FOREIGN KEY (idDisco) REFERENCES Disco(identificador)
);