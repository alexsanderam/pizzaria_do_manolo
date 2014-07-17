package pizzaria_test_case;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;

import pizzaria.Sistema;

public class PizzariaTestCase extends DatabaseTestCase{

	@Test
	public void testCadastrar_pizza() throws Exception {
		IDataSet dsAtual = getConnection().createDataSet();
	    ITable tabelaAtual = dsAtual.getTable("pizza");
	    assertEquals(1, tabelaAtual.getRowCount());
	    
	    MockSistema.cadastrar_pizza();
	    
	}
	
	/*	IDataSet dsAtual = getConnection().createDataSet();
	    ITable tabelaAtual = dsAtual.getTable("cliente");
	    assertEquals(1, tabelaAtual.getRowCount());
		
		Cliente cliente = new Cliente(“Luis”);
	    Cliente.cadastrar(cliente);
	    IDataSet dsAtual = getConnection().createDataSet();
	    ITable tabelaAtual = dsAtual.getTable(“cliente”);
	    assertEquals(1, tabelaAtual.rowCount());
	*/

	
	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:h2:mem:db", "sa", "");
		return new DatabaseConnection(conn);
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		FlatXmlDataSetBuilder dataSetBuilder = new FlatXmlDataSetBuilder();
		return dataSetBuilder.build(new FileInputStream("dataset.xml"));
	}

	protected DatabaseOperation getSetUpOperation() {
		   return  DatabaseOperation.CLEAN_INSERT;
	}

	protected DatabaseOperation getTearDownOperation(){
	   return DatabaseOperation.NONE;
	}
}
