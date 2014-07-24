package funcional;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.ControladorDominio;
import dominio.Pizza;
import excecoes.ExcecaoDAO;
import excecoes.ExcecaoDePizza;

public class CadastrarPizzaTest{
	
    private String nome = "Frango";
    private String ingredientes = "Molho, mussarela, frango e orégano";
    private Float preco = (float) 21.0;

	@Test
	public void testCadastrarPizza() throws Exception {
	    MockMantenedorDeRegistro mantenedor =  new MockMantenedorDeRegistro();

	    
	    Pizza pizza = ControladorDominio.obterInstancia().novaPizza(nome, ingredientes, preco);
	    mantenedor.incluirPizza(pizza);
	    
	    assertEquals(1, mantenedor.contarRegistros("pizzaria.Pizza"));
	}
	
	@Test(expected = ExcecaoDAO.class)
	public void testCadastrarPizzaExistente() throws ExcecaoDAO, ExcecaoDePizza {
	    MockMantenedorDeRegistro mantenedor =  new MockMantenedorDeRegistro();
	    
	    Pizza pizza = ControladorDominio.obterInstancia().novaPizza(nome, ingredientes, preco);
	    mantenedor.incluirPizza(pizza);
	    mantenedor.incluirPizza(pizza);
	}
	
	@Test(expected = ExcecaoDePizza.class)
	public void testCadastrarPizzaNomeInvalido() throws ExcecaoDePizza, ExcecaoDAO {
	    MockMantenedorDeRegistro mantenedor =  new MockMantenedorDeRegistro();
	    
	    String nome = "";
	    
	    Pizza pizza = null;

    	pizza = ControladorDominio.obterInstancia().novaPizza(nome, ingredientes, preco);
    	mantenedor.incluirPizza(pizza);	    
	}


	@Test(expected = ExcecaoDePizza.class)
	public void testCadastrarPizzaIngredientesInvalido() throws ExcecaoDePizza, ExcecaoDAO {
	    MockMantenedorDeRegistro mantenedor =  new MockMantenedorDeRegistro();

	    String ingredientes = "";

	    Pizza pizza = null;
	    
    	pizza = ControladorDominio.obterInstancia().novaPizza(nome, ingredientes, preco);
    	mantenedor.incluirPizza(pizza);
	}
	
	
	@Test(expected = ExcecaoDePizza.class)
	public void testCadastrarPizzaPrecoInvalido() throws ExcecaoDePizza, ExcecaoDAO {
	    MockMantenedorDeRegistro mantenedor =  new MockMantenedorDeRegistro();
	    
	    Float preco = (float) -1.0;
	    
	    Pizza pizza = null;

    	pizza = ControladorDominio.obterInstancia().novaPizza(nome, ingredientes, preco);
    	mantenedor.incluirPizza(pizza);
	}
	

	@Before
	public void setUp(){
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE SCHEMA pizzaria;");
		sql.append("CREATE TABLE pizzaria.Pizza(");
		sql.append("id SERIAL,");
		sql.append("nome CHARACTER VARYING UNIQUE NOT NULL,");
		sql.append("ingredientes CHARACTER VARYING NOT NULL,");
		sql.append("preco FLOAT NOT NULL,");
		sql.append("CONSTRAINT pizza_pkey PRIMARY KEY(id));");
		
		try {
			Connection conexao = ConnectionFactoryTest.obterInstancia().obterConexao();
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
		
		sql.append("DROP TABLE IF EXISTS pizzaria.Pizza;");
		sql.append("DROP SCHEMA IF EXISTS pizzaria;");

		try {
			Connection conexao = ConnectionFactoryTest.obterInstancia().obterConexao();
			PreparedStatement stmt;
			
			stmt = conexao.prepareStatement(sql.toString());
			stmt.execute();
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	
}
