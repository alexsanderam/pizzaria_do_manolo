insert into webpizzaria.Cliente(email,senha, nome,telefone,endereco) values ('email', 'senha', 'nome', 'telefone', 'endereco');
insert into webpizzaria.Pizza(nome,ingredientes,preco) values ('nome','ingredientes',0.0);
insert into webpizzaria.Pagamento(forma_de_pagamento, valor_do_troco) values ('CARTAO_DE_DEBITO',0.0);
insert into webpizzaria.Pedido(id_cliente,id_pagamento,valor,data_hora) values (1,1,0.0,current_timestamp);
