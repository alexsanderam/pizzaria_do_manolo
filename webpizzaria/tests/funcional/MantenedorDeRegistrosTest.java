package funcional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pizzariaDAO.ClienteDAO;
import pizzariaDAO.ClienteDAOConcreto;
import pizzariaDAO.ItemPedidoDAO;
import pizzariaDAO.ItemPedidoDAOConcreto;
import pizzariaDAO.PagamentoDAO;
import pizzariaDAO.PagamentoDAOConcreto;
import pizzariaDAO.PedidoDAO;
import pizzariaDAO.PedidoDAOConcreto;
import pizzariaDAO.PizzaDAO;
import pizzariaDAO.PizzaDAOConcreto;
import controlador.MantenedorDeRegistros;
import excecoes.ExcecaoDAO;

class MantenedorDeRegistroTest extends MantenedorDeRegistros {
	    	
@Override
protected ClienteDAO criarClienteDAO() throws ExcecaoDAO {
	class ClienteDAOTest extends ClienteDAOConcreto {

		public ClienteDAOTest() throws ExcecaoDAO {
			this.conexao = criarConexao();
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
			this.conexao = criarConexao();
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
			this.conexao = criarConexao();
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
		
		@Override
		protected ItemPedidoDAO criarItemPedidoDAO () throws ExcecaoDAO {
			MantenedorDeRegistroTest mantenedor = new MantenedorDeRegistroTest();
			return mantenedor.criarItemPedidoDAO();
		}
		
		@Override
		protected ClienteDAO criarClienteDAO () throws ExcecaoDAO {
			MantenedorDeRegistroTest mantenedor = new MantenedorDeRegistroTest();
			return mantenedor.criarClienteDAO();
		}
		
		@Override
		protected PagamentoDAO criarPagamentoDAO () throws ExcecaoDAO {
			MantenedorDeRegistroTest mantenedor = new MantenedorDeRegistroTest();
			return mantenedor.criarPagamentoDAO();
		}
	}
	
	return new PedidoDAOTest();
}

@Override
protected ItemPedidoDAO criarItemPedidoDAO() throws ExcecaoDAO {
	class ItemPedidoDAOTest extends ItemPedidoDAOConcreto {

		public ItemPedidoDAOTest() throws ExcecaoDAO {
			this.conexao = criarConexao();
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
	
	return new ItemPedidoDAOTest();
}

@Override
protected PagamentoDAO criarPagamentoDAO() throws ExcecaoDAO {
	class PagamentoDAOTest extends PagamentoDAOConcreto {

		public PagamentoDAOTest() throws ExcecaoDAO {
			this.conexao = criarConexao();
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
	
	return new PagamentoDAOTest();
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