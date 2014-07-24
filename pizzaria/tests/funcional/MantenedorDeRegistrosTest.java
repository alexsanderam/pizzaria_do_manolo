package funcional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pizzariaDAO.ClienteDAO;
import pizzariaDAO.ClienteDAOConcreto;
import pizzariaDAO.PedidoDAO;
import pizzariaDAO.PedidoDAOConcreto;
import pizzariaDAO.PizzaDAO;
import pizzariaDAO.PizzaDAOConcreto;
import controlador.MantenedorDeRegistros;
import excecoes.ExcecaoDAO;

class MockMantenedorDeRegistro extends MantenedorDeRegistros {
	    	
	@Override
	protected ClienteDAO criarClienteDAO() throws ExcecaoDAO {
		class ClienteDAOTest extends ClienteDAOConcreto {

			public ClienteDAOTest() throws ExcecaoDAO {
				super();
			}

			@Override
			protected Connection criarConexao() {
				try {
					return ConnectionFactoryTest.obterInstancia().obterConexao();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return null;
			}
		}
		
		return new ClienteDAOTest();
	}
	
	@Override
	protected PizzaDAO criarPizzaDAO() throws ExcecaoDAO {
		class PizzaDAOTest extends PizzaDAOConcreto {

			public PizzaDAOTest() throws ExcecaoDAO {
				super();
			}

			@Override
			protected Connection criarConexao() {
				try {
					return ConnectionFactoryTest.obterInstancia().obterConexao();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return null;
			}
		}
		
		return new PizzaDAOTest();
	}
	
	@Override
	protected PedidoDAO criarPedidoDAO() throws ExcecaoDAO {
		class PedidoDAOTest extends PedidoDAOConcreto {

			public PedidoDAOTest() throws ExcecaoDAO {
				super();
			}

			@Override
			protected Connection criarConexao() {
				try {
					return ConnectionFactoryTest.obterInstancia().obterConexao();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return null;
			}
		}
		
		return new PedidoDAOTest();
	}
	
	public int contarRegistros(String nomeTabela){
		try {
			Connection conexao = ConnectionFactoryTest.obterInstancia().obterConexao();
			PreparedStatement stmt = conexao.prepareStatement("select count(*) from " + nomeTabela);
			
		    ResultSet rs = stmt.executeQuery();
		    if (rs.next()){
		    	return rs.getInt(1);
		    }
		} catch (ExcecaoDAO | SQLException e1) {
			e1.printStackTrace();
		} 		
	    
	    return Integer.valueOf(-1);
	}
}