package funcional;

import java.sql.Connection;
import java.sql.PreparedStatement;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.ControladorDominio;
import dominio.Cliente;
import dominio.Pedido;
import dominio.Pizza;
import excecoes.ExcecaoDAO;
import excecoes.ExcecaoDeCliente;
import excecoes.ExcecaoDePedido;
import excecoes.ExcecaoDePizza;

public class CadastrarPedidoTest extends TestCase{
	private String nomePizza = "Frango";
    private String ingredientes = "Molho, mussarela, frango e orégano";
    private Float preco = (float) 21.0;
    private String telefone = "2668-3641";
    private String nomeCliente = "Manoel Nolo Clementino";
    private String endereco = "Av. Governador Roberto Silveira S/N";

	@Test
	public void testCadastrarPedido() throws Exception {  
	    MockMantenedorDeRegistro mantenedor =  new MockMantenedorDeRegistro();
	 
	    
	    Pizza pizza = ControladorDominio.obterInstancia().novaPizza(nomePizza, ingredientes, preco);
	    mantenedor.incluirPizza(pizza);
  
	    
	    Cliente cliente = ControladorDominio.obterInstancia().novoCliente(telefone, nomeCliente, endereco);
	    mantenedor.incluirCliente(cliente);
	    
	    Pedido pedido = ControladorDominio.obterInstancia().novoPedido(cliente, pizza, null, 1);
	    mantenedor.incluirPedido(pedido);
	    
	    assertEquals(1, mantenedor.contarRegistros("pizzaria.Pedido"));
	}

	
	@Test
	public void testCadastrarPedidoPizzaNaoExistente() throws ExcecaoDePizza, ExcecaoDeCliente, ExcecaoDAO, ExcecaoDePedido{  
	    MockMantenedorDeRegistro mantenedor =  new MockMantenedorDeRegistro();

	    Pizza pizza = ControladorDominio.obterInstancia().novaPizza(nomePizza, ingredientes, preco);
	    
	    /*Pizza nao foi incluida na base de dados*/
    
	    Cliente cliente = ControladorDominio.obterInstancia().novoCliente(telefone, nomeCliente, endereco);
	    mantenedor.incluirCliente(cliente);
	    
	    Pedido pedido = ControladorDominio.obterInstancia().novoPedido(cliente, pizza, null, 1);
	    try {
	    	mantenedor.incluirPedido(pedido);
	    } catch (Exception e) {
	    	assertEquals(ExcecaoDAO.class, e.getClass());
	    }
	    
	}
	
	@Test
	public void testCadastrarPedidoClienteNaoExistente() throws ExcecaoDePedido, ExcecaoDePizza, ExcecaoDAO, ExcecaoDeCliente {  
	    MockMantenedorDeRegistro mantenedor =  new MockMantenedorDeRegistro();

	    
	    Pizza pizza = ControladorDominio.obterInstancia().novaPizza(nomePizza, ingredientes, preco);
	    mantenedor.incluirPizza(pizza);
	    
	    Cliente cliente = ControladorDominio.obterInstancia().novoCliente(telefone, nomeCliente, endereco);
	    
	    /*Cliente nao foi incluido na base de dados*/
	    
	    Pedido pedido = ControladorDominio.obterInstancia().novoPedido(cliente, pizza, null, 1);
	    
	    try {
	    	mantenedor.incluirPedido(pedido);
	    } catch (Exception e) {
	    	assertEquals(ExcecaoDAO.class, e.getClass());
	    }
	}
	
	
	@Before
	public void setUp(){
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE SCHEMA pizzaria;");
		
		sql.append("CREATE TABLE pizzaria.Cliente(");
		sql.append("id SERIAL,");
		sql.append("telefone CHARACTER VARYING NOT NULL,");
		sql.append("nome CHARACTER VARYING NOT NULL,");
		sql.append("endereco CHARACTER VARYING NOT NULL,");
		sql.append("CONSTRAINT cliente_pkey PRIMARY KEY (id),");
		sql.append("CONSTRAINT telefone_unique UNIQUE (telefone));");
		
		sql.append("CREATE TABLE pizzaria.Pizza(");
		sql.append("id SERIAL,");
		sql.append("nome CHARACTER VARYING UNIQUE NOT NULL,");
		sql.append("ingredientes CHARACTER VARYING NOT NULL,");
		sql.append("preco FLOAT NOT NULL,");
		sql.append("CONSTRAINT pizza_pkey PRIMARY KEY(id));");
		
		sql.append("CREATE TABLE pizzaria.Pedido(");
		sql.append("id_cliente INTEGER NOT NULL,");
		sql.append("id_pizza INTEGER NOT NULL,");
		sql.append("data_hora TIMESTAMP WITHOUT TIME ZONE NOT NULL,");
		sql.append("quantidade INTEGER,");
		sql.append("CONSTRAINT pk PRIMARY KEY (id_cliente, id_pizza, data_hora),");
		sql.append("CONSTRAINT fk_cardapio FOREIGN KEY (id_pizza)");
		sql.append("REFERENCES pizzaria.pizza (id)");
		sql.append("ON UPDATE NO ACTION ON DELETE NO ACTION,");
		sql.append("CONSTRAINT fk_cliente FOREIGN KEY (id_cliente)");
		sql.append("REFERENCES pizzaria.cliente (id)");		
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
		
		sql.append("DROP TABLE IF EXISTS pizzaria.Pedido;");
		sql.append("DROP TABLE IF EXISTS pizzaria.Cliente;");
		sql.append("DROP TABLE IF EXISTS pizzaria.Pizza;");
		sql.append("DROP SCHEMA IF EXISTS pizzaria;");

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
