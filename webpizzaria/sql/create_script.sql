﻿DROP TABLE IF EXISTS webpizzaria.ItemPedido CASCADE;
DROP TABLE IF EXISTS webpizzaria.Pedido CASCADE;
DROP TABLE IF EXISTS webpizzaria.Pagamento CASCADE;
DROP TYPE IF EXISTS webpizzaria.FormaDePagamento CASCADE;
DROP TABLE IF EXISTS webpizzaria.Cliente CASCADE;
DROP TABLE IF EXISTS webpizzaria.Pizza CASCADE;
DROP SCHEMA IF EXISTS webpizzaria CASCADE;


CREATE SCHEMA webpizzaria
  AUTHORIZATION postgres;


CREATE TABLE webpizzaria.Cliente(
	id SERIAL,
	email CHARACTER VARYING NOT NULL,
	senha CHARACTER VARYING NOT NULL,
	nome CHARACTER VARYING NOT NULL,
	telefone CHARACTER VARYING NOT NULL,
	endereco CHARACTER VARYING NOT NULL,

	CONSTRAINT cliente_pkey PRIMARY KEY (id),
	CONSTRAINT telefone_unique UNIQUE (telefone),
	CONSTRAINT email_unique UNIQUE (email)
);

CREATE TABLE webpizzaria.Pizza(
	id SERIAL,
	nome CHARACTER VARYING UNIQUE NOT NULL,
	ingredientes CHARACTER VARYING NOT NULL,
	preco FLOAT NOT NULL,

	CONSTRAINT pizza_pkey PRIMARY KEY(id)
);


CREATE TYPE webpizzaria.FormaDePagamento AS ENUM(
	'DINHEIRO_SEM_TROCO',
	'DINHEIRO_COM_TROCO',
	'CARTAO_DE_CREDITO', 
	'CARTAO_DE_DEBITO'
);


CREATE TABLE webpizzaria.Pagamento(
	id SERIAL,
	forma_de_pagamento webpizzaria.FormaDePagamento NOT NULL,
	valor_do_troco FLOAT,

	CONSTRAINT pagamento_pkey PRIMARY KEY(id)
);


CREATE TABLE webpizzaria.Pedido(
	id SERIAL,
	id_cliente INTEGER NOT NULL,
	id_pagamento INTEGER NOT NULL,
	valor FLOAT NOT NULL,
	data_hora TIMESTAMP WITHOUT TIME ZONE NOT NULL,

	CONSTRAINT pedido_pkey PRIMARY KEY(id),
	CONSTRAINT pedido_unique UNIQUE (id_cliente, data_hora),

	CONSTRAINT fk_cliente FOREIGN KEY (id_cliente)
	REFERENCES webpizzaria.cliente (id),

	CONSTRAINT fk_pagamento FOREIGN KEY (id_pagamento)
	REFERENCES webpizzaria.pagamento (id)
);

CREATE TABLE webpizzaria.ItemPedido(
	id_pedido INTEGER NOT NULL,
	id_pizza INTEGER NOT NULL,
	quantidade INTEGER NOT NULL,

	CONSTRAINT item_pedido_pkey PRIMARY KEY(id_pedido, id_pizza),

	CONSTRAINT fk_pedido FOREIGN KEY (id_pedido)
	REFERENCES webpizzaria.pedido (id),

	CONSTRAINT fk_pizza FOREIGN KEY (id_pizza)
	REFERENCES webpizzaria.pizza (id)	
);