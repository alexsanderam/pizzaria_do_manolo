package funcional;

import java.sql.Connection;
import java.sql.PreparedStatement;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.ControladorDominio;
import dominio.Cliente;
import excecoes.ExcecaoDAO;
import excecoes.ExcecaoDeCliente;

public class CadastrarClienteTest extends TestCase{
	
    private String telefone = "2668-3641";
    private String nome = "Manoel Nolo Clementino";
    private String endereco = "Av. Governador Roberto Silveira S/N";

	@Test
	public void testCadastrarCliente() throws Exception {
	    MockMantenedorDeRegistro mantenedor =  new MockMantenedorDeRegistro();
	    
	    Cliente cliente = ControladorDominio.obterInstancia().novoCliente(telefone, nome, endereco);
	    mantenedor.incluirCliente(cliente);
	    
	    assertEquals(1, mantenedor.contarRegistros("pizzaria.Cliente"));
	}
	
	
	@Test
	public void testCadastrarClienteComNomeInvalido() throws ExcecaoDeCliente, ExcecaoDAO{
	    MockMantenedorDeRegistro mantenedor =  new MockMantenedorDeRegistro();
	    
	    String nome = "";
	    
	    try {
	    	Cliente cliente = ControladorDominio.obterInstancia().novoCliente(telefone, nome, endereco);
		    mantenedor.incluirCliente(cliente);
	    } catch (Exception e) {
	    	assertEquals(ExcecaoDeCliente.class, e.getClass());
	    }
	    
	}
	
	
	
	@Test
	public void testCadastrarClienteComTelefoneInvalido() throws ExcecaoDeCliente, ExcecaoDAO {
	    MockMantenedorDeRegistro mantenedor =  new MockMantenedorDeRegistro();
	    
	    String telefone = "";
	    
	    try {
	    	Cliente cliente = ControladorDominio.obterInstancia().novoCliente(telefone, nome, endereco);
		    mantenedor.incluirCliente(cliente);
	    } catch (Exception e) {
	    	assertEquals(ExcecaoDeCliente.class, e.getClass());
	    }
	}

	
	@Test(expected = ExcecaoDeCliente.class)
	public void testCadastrarClienteComEnderecoInvalido() throws ExcecaoDeCliente, ExcecaoDAO {
	    MockMantenedorDeRegistro mantenedor =  new MockMantenedorDeRegistro();
	    
	    String endereco = "";
	    
	    try {
	    	Cliente cliente = ControladorDominio.obterInstancia().novoCliente(telefone, nome, endereco);
		    mantenedor.incluirCliente(cliente);
	    } catch (Exception e) {
	    	assertEquals(ExcecaoDeCliente.class, e.getClass());
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
		
		sql.append("DROP TABLE IF EXISTS pizzaria.Cliente;");
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
