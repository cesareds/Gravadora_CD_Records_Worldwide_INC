-- Gravadora CD Records Worldwide INC.

--Entidades:
CREATE TABLE Criador(
                        id bigserial PRIMARY KEY,
                        nome varchar(255),
                        descricao varchar(255),
                        genero varchar(255)
);
CREATE TABLE Musico (
    cep varchar(8),
    rua varchar(255),
    cidade varchar(255),
    estado varchar(255),
    telefone varchar(25) UNIQUE, -- Add unique constraint here
    PRIMARY KEY (id) -- Assuming id is inherited from Criador
) INHERITS (Criador);

CREATE TABLE Banda (
    id bigserial PRIMARY KEY, -- Add primary key constraint here
    dataDeFormacao date
) INHERITS (Criador);

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
                      FOREIGN KEY (idMusico) REFERENCES Musico(id)
);
CREATE TABLE Integrar(
                         id bigserial PRIMARY KEY,
                         idMusico bigint,
                         idBanda bigint,
                         FOREIGN KEY (idMusico) REFERENCES Musico(id),
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

-- f1:
--     Descrição: Este gatilho é acionado antes da inserção ou atualização de um disco e verifica se o número de platinas não é negativo. Se for, uma exceção é levantada, impedindo a operação.

create or replace function verifica_platinas_nao_negativos() returns trigger as
    $$
    begin
        if new.platinas < 0 then
            raise exception 'O número de platinas não pode ser menor que 0!';
        elsif  new.preco < 0 then
            raise exception 'O preço não pode ser menor que 0!';
        end if;
        return new;
    end
    $$ language plpgsql;

create or replace trigger trigger_verifica_platinas
    before insert or update on disco
    for each row
    execute function  verifica_platinas_nao_negativos();

-- f2
--     Descrição: Este gatilho é acionado após a inserção de um músico e verifica se já existe um músico que possui esse número de telefone

create or replace function verifica_musico() returns trigger as
    $$
    declare
        contador INT;
    begin
        select COUNT(*) into contador from musico where telefone = new.telefone;

        if contador > 0 then
            raise exception 'Já existe um musico com esse telefone';
        end if;

        return new;
    end;
    $$ language plpgsql;

create or replace trigger trigger_verifica_criador
    before insert or update on musico
    for each row
    execute function verifica_musico();

-- f3
--      Descrição: Esta função verifica se há registros duplicados na tabela de participação de músicas e remove as duplicatas, mantendo apenas uma entrada para cada combinação de música e criador.
--      Gatilho: Pode ser agendada para ser executada periodicamente ou acionada manualmente quando necessário.

create or replace function remove_participacao_duplicadas() returns trigger as
    $$
    declare
        contador int;
    begin
        select COUNT(*) into contador
        from participacao p
        where new.idmusica = p.idmusica and new.idcriador = p.idcriador;

        if contador > 0 then
            raise exception 'Nao pode haver musicas duplicadas para um mesmo músico e cansão';
        end if;

        return new;
    end;
    $$ language plpgsql;

create trigger trigger_remove_participacao_duplicada
    before insert or update on participacao
    for each row
    execute function remove_participacao_duplicadas();

-- f4
--      Descrição: Esta função raise exception se ja existir o mesmo instrumento

create or replace function remove_copias_instrumento() returns trigger as
    $$
    declare
        contador INT;
    begin
        select COUNT(*) into contador from instrumento
                where new.marca = marca and nome = nome and new.tipo = tipo;

        if contador > 0 then
            raise exception 'Esse instumento já existe';
        end if;

        return new;
    end;
    $$ language plpgsql;

create or replace trigger trigger_remove_copia_instument
    before insert or update on instrumento
    for each row
    execute function remove_copias_instrumento();

-- f5 Verifica se ja existe criador antes de inserir banda

CREATE OR REPLACE FUNCTION inserir_criador()
RETURNS TRIGGER AS $$
DECLARE
    novo_id BIGINT;
BEGIN
    -- Insere um novo criador e captura o id gerado
    INSERT INTO Criador (nome, descricao, genero)
    VALUES (NEW.nome, NEW.descricao, NEW.genero)
    RETURNING id INTO novo_id;

    -- Atribui o id gerado ao novo registro de Musico ou Banda
    NEW.id := novo_id;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_inserir_criador_musico
BEFORE INSERT ON Musico
FOR EACH ROW
EXECUTE FUNCTION inserir_criador();

CREATE TRIGGER trigger_inserir_criador_banda
BEFORE INSERT ON Banda
FOR EACH ROW
EXECUTE FUNCTION inserir_criador();


CREATE OR REPLACE FUNCTION mostrarIncluir() RETURNS TABLE (nome1 varchar(255), identificador bigint, nome2 varchar(255), id bigint)
AS
$$
BEGIN
  RETURN QUERY
  SELECT DISTINCT d.titulo, d.identificador, p.nome, p.id
  FROM incluir inc
  JOIN disco d ON d.identificador = pro.iddisco
  JOIN musica m ON m.id = pro.idprodutor;
END;
$$
language plpgsql;

CREATE OR REPLACE FUNCTION mostrarProduzir() RETURNS TABLE (nome1 varchar(255), identificador bigint, nome2 varchar(255), id bigint)
AS
$$
BEGIN
  RETURN QUERY
  SELECT DISTINCT d.titulo, d.identificador, p.nome, p.id
  FROM produzir pro
  JOIN disco d ON d.identificador = pro.iddisco
  JOIN produtor p ON p.id = pro.idprodutor;
END;
$$
language plpgsql;

CREATE OR REPLACE FUNCTION mostrarTocar() RETURNS TABLE (nome1 varchar(255), identificador bigint, nome2 varchar(255), id bigint)
AS
$$
BEGIN
  RETURN QUERY
  SELECT DISTINCT i.nome, i.codigointerno, m.nome, m.id
  FROM tocar toc
  JOIN instrumento i ON i.codigointerno = toc.idinstrumento
  JOIN musico m ON m.id = toc.idmusico;
END;
$$
language plpgsql;
CREATE OR REPLACE FUNCTION mostrarParticipacao() RETURNS TABLE (nome1 varchar(255), identificador bigint, nome2 varchar(255), id bigint)
AS
$$
BEGIN
  RETURN QUERY
  SELECT DISTINCT m.titulo, m.id, c.nome, c.id
  FROM participacao par
  JOIN musica m ON m.id = par.idmusica
  JOIN criador c ON c.id = par.idcriador;
END;
$$
language plpgsql;

CREATE OR REPLACE FUNCTION mostrarLancamento() RETURNS TABLE (nome1 varchar(255), identificador bigint, nome2 varchar(255), id bigint)
AS
$$
BEGIN
  RETURN QUERY
  SELECT DISTINCT d.titulo, d.identificador, c.nome, c.id
  FROM lancamento lan
  JOIN disco d ON d.identificador = lan.iddisco
  JOIN criador c ON c.id = lan.idcriador;
END;
$$
language plpgsql;

CREATE OR REPLACE FUNCTION mostrarIntegramentacao() RETURNS TABLE (nome1 varchar(255), identificador bigint, nome2 varchar(255), id bigint)
AS
$$
BEGIN
  RETURN QUERY
  SELECT DISTINCT m.nome, m.id, b.nome, b.id
  FROM integrar i
  JOIN musico m ON m.id = i.idmusico
  JOIN banda b ON b.id = i.idbanda;
END;
$$
language plpgsql;
