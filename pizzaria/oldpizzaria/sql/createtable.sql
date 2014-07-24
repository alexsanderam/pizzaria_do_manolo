create table cliente (telefone varchar primary key, nome varchar, endereco varchar);
create table cardapio (nome_pizza varchar primary key, ingredientes varchar, preco varchar);
create table pedido (telefone varchar, 
	data_hora timestamp, 
	nome_pizza varchar, 
	quantidade int, 
	constraint pk primary key (telefone, data_hora, nome_pizza), 
	constraint fk_cliente foreign key (telefone) references cliente (telefone), 
	constraint fk_cardapio foreign key (nome_pizza) references cardapio(nome_pizza));
