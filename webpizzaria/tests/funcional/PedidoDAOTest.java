package funcional;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.ControladorDominio;
import dominio.Cliente;
import dominio.EnumFormaDePagamento;
import dominio.Pagamento;
import dominio.Pedido;
import dominio.Pizza;
import excecoes.ExcecaoDAO;
import excecoes.ExcecaoDeCliente;
import excecoes.ExcecaoDePedido;
import excecoes.ExcecaoDePizza;

public class PedidoDAOTest{
	
    private String nomePizza = "Frango";
    private String ingredientes = "Molho, mussarela, frango e orégano";
    private Float preco = 21.0f;

    private String nomePizza2 = "Bacon";
    private String ingredientes2 = "Muito bacon";
    private Float preco2 = 31.0f;

    
	private String email = "manoel@clementino.com";
	private String senha = "12345";
    private String telefone = "2668-3641";
    private String nome = "Manoel Nolo Clementino";
    private String endereco = "Av. Governador Roberto Silveira S/N";

    private Float valorRecebido = 200f;
    private EnumFormaDePagamento formaDePagamento = EnumFormaDePagamento.DINHEIRO_COM_TROCO;
    
    
	@Test
	public void testIncluirPedido() throws Exception {  
	    MockMantenedorDeRegistro mantenedor =  new MockMantenedorDeRegistro();
	    
	    Pizza pizza = ControladorDominio.obterInstancia().novaPizza(nomePizza, ingredientes, preco);
	    mantenedor.incluirPizza(pizza);
	    
	    Pizza pizza2 = ControladorDominio.obterInstancia().novaPizza(nomePizza2, ingredientes2, preco2);
	    mantenedor.incluirPizza(pizza2);
	    
	    Cliente cliente = ControladorDominio.obterInstancia().novoCliente(telefone, email, senha, nome, endereco);
	    mantenedor.incluirCliente(cliente);
	    
	    Pagamento pagamento = ControladorDominio.obterInstancia().novoPagamento(formaDePagamento, valorRecebido);
	    
	    Pedido pedido = ControladorDominio.obterInstancia().novoPedido(cliente);
	    pedido.incluirPizza(pizza, 2);
	    pedido.incluirPizza(pizza2, 5);
	    
	    mantenedor.incluirPedido(pedido, pagamento);
	    
	    assertEquals(1, mantenedor.contarRegistros("webpizzaria.Pedido"));
	}

	
	@Test(expected = ExcecaoDAO.class)
	public void testCadastrarPedidoPizzaNaoExistente() throws ExcecaoDePizza, ExcecaoDeCliente, ExcecaoDAO, ExcecaoDePedido{  
	    MockMantenedorDeRegistro mantenedor =  new MockMantenedorDeRegistro();

	    Pizza pizza = ControladorDominio.obterInstancia().novaPizza(nomePizza, ingredientes, preco);
	    
	    /*Pizza nao foi incluida na base de dados*/
    
	    Cliente cliente = ControladorDominio.obterInstancia().novoCliente(telefone, nomeCliente, endereco);
	    mantenedor.incluirCliente(cliente);
	    
	    Pedido pedido = ControladorDominio.obterInstancia().novoPedido(cliente, pizza, 1);
	    mantenedor.incluirPedido(pedido);
	}
	
	@Test(expected = ExcecaoDAO.class)
	public void testCadastrarPedidoClienteNaoExistente() throws ExcecaoDePedido, ExcecaoDePizza, ExcecaoDAO, ExcecaoDeCliente {  
	    MockMantenedorDeRegistro mantenedor =  new MockMantenedorDeRegistro();

	    
	    Pizza pizza = ControladorDominio.obterInstancia().novaPizza(nomePizza, ingredientes, preco);
	    mantenedor.incluirPizza(pizza);
	    
	    Cliente cliente = ControladorDominio.obterInstancia().novoCliente(telefone, nomeCliente, endereco);
	    
	    /*Cliente nao foi incluido na base de dados*/
	    
	    Pedido pedido = ControladorDominio.obterInstancia().novoPedido(cliente, pizza, 1);

	    mantenedor.incluirPedido(pedido);
	}
	
	
	@Before
	public void setUp(){
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE SCHEMA webpizzaria;");

		sql.append("CREATE TABLE webpizzaria.Cliente(");
		sql.append("id SERIAL,");
		sql.append("email CHARACTER VARYING NOT NULL,");
		sql.append("senha CHARACTER VARYING NOT NULL,");
		sql.append("nome CHARACTER VARYING NOT NULL,");
		sql.append("telefone CHARACTER VARYING NOT NULL,");
		sql.append("endereco CHARACTER VARYING NOT NULL,");
		sql.append("CONSTRAINT cliente_pkey PRIMARY KEY (id),");
		sql.append("CONSTRAINT email_unique UNIQUE (email),");
		sql.append("CONSTRAINT telefone_unique UNIQUE (telefone));");
		
		sql.append("CREATE TABLE webpizzaria.Pizza(");
		sql.append("id SERIAL,");
		sql.append("nome CHARACTER VARYING UNIQUE NOT NULL,");
		sql.append("ingredientes CHARACTER VARYING NOT NULL,");
		sql.append("preco FLOAT NOT NULL,");
		sql.append("CONSTRAINT pizza_pkey PRIMARY KEY(id));");
		
		sql.append("CREATE TABLE webpizzaria.pagamento(");
		sql.append("id serial NOT NULL,");
		sql.append("forma_de_pagamento webpizzaria.formadepagamento NOT NULL,");
		sql.append("valor_recebido double precision,");
		sql.append("CONSTRAINT pagamento_pkey PRIMARY KEY (id));");
		
		sql.append("CREATE TABLE webpizzaria.Pedido(");
		sql.append("id SERIAL NOT NULL,");
		sql.append("id_cliente_fk INTEGER NOT NULL,");
		sql.append("id_pagamento_fk INTEGER NOT NULL,");
		sql.append("data_hora TIMESTAMP WITHOUT TIME ZONE NOT NULL,");
		sql.append("CONSTRAINT pedido_pkey PRIMARY KEY (id),");
		sql.append("CONSTRAINT fk_cliente FOREIGN KEY (id_cliente_fk)");
		sql.append("REFERENCES webpizzaria.cliente (id) MATCH SIMPLE");
		sql.append("ON UPDATE NO ACTION ON DELETE NO ACTION,");
		sql.append("CONSTRAINT fk_pagamento FOREIGN KEY (id_pagamento_fk)");
		sql.append("REFERENCES webpizzaria.pagamento (id) MATCH SIMPLE");
		sql.append("ON UPDATE NO ACTION ON DELETE NO ACTION,");
		sql.append("CONSTRAINT pedido_unique UNIQUE (id_cliente_fk, data_hora));");
		  
		sql.append("CREATE TABLE webpizzaria.itempedido)");
		sql.append("id_pedido_fk integer NOT NULL,");
		sql.append("id_pizza_fk integer NOT NULL,");
		sql.append("quantidade integer NOT NULL,");
		sql.append("CONSTRAINT item_pedido_pkey PRIMARY KEY (id_pedido_fk, id_pizza_fk),");
		sql.append("CONSTRAINT fk_pedido FOREIGN KEY (id_pedido_fk)");
		sql.append("REFERENCES webpizzaria.pedido (id) MATCH SIMPLE");
		sql.append("ON UPDATE NO ACTION ON DELETE NO ACTION,");
		sql.append("CONSTRAINT fk_pizza FOREIGN KEY (id_pizza_fk)");
		sql.append("REFERENCES webpizzaria.pizza (id) MATCH SIMPLE");
		sql.append("ON UPDATE NO ACTION ON DELETE NO ACTION);");
		
		try {
			Connection conexao = MockConnectionFactory.obterInstancia().obterConexao();
			PreparedStatement stmt;
			
			stmt = conexao.prepareStatement(sql.toString());
			stmt.execute();
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	
	@After
	public void tearDown(){
		StringBuilder sql = new StringBuilder();
		
		sql.append("DROP TABLE IF EXISTS webpizzaria.ItemPedido ON CASCADE;");
		sql.append("DROP TABLE IF EXISTS webpizzaria.Pedido ON CASCADE;");
		sql.append("DROP TABLE IF EXISTS webpizzaria.Pagamento ON CASCADE;");
		sql.append("DROP TABLE IF EXISTS webpizzaria.Cliente ON CASCADE;");
		sql.append("DROP TABLE IF EXISTS webpizzaria.Pizza ON CASCADE;");
		sql.append("DROP SCHEMA IF EXISTS webpizzaria ON CASCADE;");

		try {
			Connection conexao = MockConnectionFactory.obterInstancia().obterConexao();
			PreparedStatement stmt;
			
			stmt = conexao.prepareStatement(sql.toString());
			stmt.execute();
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	
}
