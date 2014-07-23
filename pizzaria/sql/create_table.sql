DROP TABLE IF EXISTS pizzaria.Pizza CASCADE;
DROP TABLE IF EXISTS pizzaria.Cliente CASCADE;
DROP TABLE IF EXISTS pizzaria.Pedido CASCADE;
DROP SCHEMA IF EXISTS pizzaria CASCADE;

CREATE SCHEMA pizzaria
  AUTHORIZATION postgres;

CREATE TABLE pizzaria.Cliente(
	id SERIAL,
	telefone CHARACTER VARYING NOT NULL,
	nome CHARACTER VARYING NOT NULL,
	endereco CHARACTER VARYING NOT NULL,

	CONSTRAINT cliente_pkey PRIMARY KEY (id),
	CONSTRAINT telefone_unique UNIQUE (telefone)
);

CREATE TABLE pizzaria.Pizza(
	id SERIAL,
	nome CHARACTER VARYING UNIQUE NOT NULL,
	ingredientes CHARACTER VARYING NOT NULL,
	preco FLOAT NOT NULL,

	CONSTRAINT pizza_pkey PRIMARY KEY(id)
);

CREATE TABLE pizzaria.Pedido(
	id_cliente_fk INTEGER NOT NULL,
	id_pizza_fk INTEGER NOT NULL,
	data_hora TIMESTAMP WITHOUT TIME ZONE NOT NULL,
	quantidade INTEGER,
	
	CONSTRAINT pk PRIMARY KEY (id_cliente_fk, id_pizza_fk, data_hora),
	CONSTRAINT fk_pizza FOREIGN KEY (id_pizza_fk)
	REFERENCES pizzaria.pizza (id)
	ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_cliente FOREIGN KEY (id_cliente_fk)
	REFERENCES pizzaria.cliente (id)
	
	ON UPDATE NO ACTION ON DELETE NO ACTION
);

