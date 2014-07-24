package funcional;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.ControladorDominio;
import dominio.Pizza;
import excecoes.ExcecaoDAO;
import excecoes.ExcecaoDePizza;

public class ManterPizzaTest{
	
    private String nome = "Frango";
    private String ingredientes = "Molho, mussarela, frango e orégano";
    private Float preco = 21.0f;

	@Test
	public void testIncluirPizza() throws ExcecaoDAO, ExcecaoDePizza {
	    MantenedorDeRegistroTest mantenedor =  new MantenedorDeRegistroTest();
	    
	    Pizza pizza = ControladorDominio.obterInstancia().novaPizza(nome, ingredientes, preco);
	    mantenedor.incluirPizza(pizza);
	    
	    assertEquals(1, mantenedor.contarRegistros("webpizzaria.Pizza"));
	}
	
	@Test(expected = ExcecaoDAO.class)
	public void testIncluirPizzaExistente() throws ExcecaoDAO, ExcecaoDePizza {
	    MantenedorDeRegistroTest mantenedor =  new MantenedorDeRegistroTest();
	    
	    Pizza pizza = ControladorDominio.obterInstancia().novaPizza(nome, ingredientes, preco);
	    mantenedor.incluirPizza(pizza);
	    mantenedor.incluirPizza(pizza);
	}
	
	@Test(expected = ExcecaoDePizza.class)
	public void testIncluirPizzaNomeInvalido() throws ExcecaoDePizza, ExcecaoDAO {
	    MantenedorDeRegistroTest mantenedor =  new MantenedorDeRegistroTest();
	    
	    String nome = "";
	    
	    Pizza pizza = null;
    	pizza = ControladorDominio.obterInstancia().novaPizza(nome, ingredientes, preco);
    	mantenedor.incluirPizza(pizza);
	}


	@Test(expected = ExcecaoDePizza.class)
	public void testIncluirPizzaIngredientesInvalido() throws ExcecaoDePizza, ExcecaoDAO {
	    MantenedorDeRegistroTest mantenedor =  new MantenedorDeRegistroTest();

	    String ingredientes = "";

	    Pizza pizza = null;
    	pizza = ControladorDominio.obterInstancia().novaPizza(nome, ingredientes, preco);
    	mantenedor.incluirPizza(pizza);
	}
	
	
	@Test(expected = ExcecaoDePizza.class)
	public void testIncluirPizzaPrecoInvalido() throws ExcecaoDePizza, ExcecaoDAO {
	    MantenedorDeRegistroTest mantenedor =  new MantenedorDeRegistroTest();
	    
	    Float preco = (float) -1.0;
	    
	    Pizza pizza = null;
    	pizza = ControladorDominio.obterInstancia().novaPizza(nome, ingredientes, preco);
    	mantenedor.incluirPizza(pizza);
	}
	
	@Test
	public void testObterPizzaPorNome() throws ExcecaoDePizza, ExcecaoDAO{
		MantenedorDeRegistroTest mantenedor =  new MantenedorDeRegistroTest();
	
		Pizza pizza = ControladorDominio.obterInstancia().novaPizza("Bacon", "Muito bacon", 30f);
		mantenedor.incluirPizza(pizza);
		Pizza pizzaEcontrada = mantenedor.obterPizzaPeloNome(pizza.obterNomePizza());
		assertEquals(pizza.obterId(), pizzaEcontrada.obterId());
	}
	
	@Test
	public void testObterPizzaPorId() throws ExcecaoDePizza, ExcecaoDAO{
		MantenedorDeRegistroTest mantenedor =  new MantenedorDeRegistroTest();
	
		Pizza pizza = ControladorDominio.obterInstancia().novaPizza("Bacon", "Muito bacon", 30f);
		mantenedor.incluirPizza(pizza);
		Pizza pizzaEcontrada = mantenedor.obterPizzaPeloIdentificador(pizza.obterId());
		assertEquals(pizza.obterNomePizza(), pizzaEcontrada.obterNomePizza());
	}

	@Test(expected = ExcecaoDAO.class)
	public void testObterPizzaNaoExistentePorNome() throws ExcecaoDePizza, ExcecaoDAO{
		MantenedorDeRegistroTest mantenedor =  new MantenedorDeRegistroTest();
		String nomeDaPizza = "Bacon";
		mantenedor.obterPizzaPeloNome(nomeDaPizza);
	}

	@Test(expected = ExcecaoDAO.class)
	public void testObterPizzaNaoExistentePorId() throws ExcecaoDePizza, ExcecaoDAO{
		MantenedorDeRegistroTest mantenedor =  new MantenedorDeRegistroTest();
		Long id = 1l;
		mantenedor.obterPizzaPeloIdentificador(id);
	}
	
	@Test
	public void testObterTodasAsPizza() throws ExcecaoDePizza, ExcecaoDAO{
		MantenedorDeRegistroTest mantenedor =  new MantenedorDeRegistroTest();
		Pizza pizza = ControladorDominio.obterInstancia().novaPizza("Bacon", "Muito bacon", 30f);
		mantenedor.incluirPizza(pizza);
		pizza = ControladorDominio.obterInstancia().novaPizza(nome, ingredientes, preco);
		mantenedor.incluirPizza(pizza);
		
		int quantidadeDePizzas = 2;
		Collection<Pizza> pizzas = mantenedor.obterTodasAsPizzas();
		assertEquals(quantidadeDePizzas, pizzas.size());
	}
	
	@Before
	public void setUp(){
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE SCHEMA webpizzaria;");
		sql.append("CREATE TABLE webpizzaria.Pizza(");
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
		
		sql.append("DROP TABLE IF EXISTS webpizzaria.Pizza;");
		sql.append("DROP SCHEMA IF EXISTS webpizzaria;");

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
