package pizzariaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pizzaria.Cliente;
import excecoes.ExcecaoDAO;
import excecoes.ExcecaoDeCliente;

public class ClienteDAOConcreto implements ClienteDAO{
	
	private Connection conexao = null;
	
	
	public ClienteDAOConcreto() throws ExcecaoDAO{
		this.conexao = criarConexao();
	}
	
	
	private Connection criarConexao() throws ExcecaoDAO{
		return ConnectionFactoryConcreto.obterInstancia().obterConexao();
	}
	
	
	public void incluir(Cliente cliente) throws ExcecaoDAO {
		
		if(conexao == null)
			throw new ExcecaoDAO("cliete_dao.conexao_nao_estabelecida");
		
		String sql = "INSERT INTO pizzaria.Cliente(telefone, nome, endereco) values (?, ?, ?) RETURNING id";
		PreparedStatement stmt;
		
		try {
			stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, cliente.obterTelefone());
			stmt.setString(2, cliente.obterNome());
			stmt.setString(3, cliente.obterEndereco());
			
			stmt.execute();
			
		} catch (SQLException e) {

			throw new ExcecaoDAO("cliente_dao.nao_foi_possivel_incluir_o_cliente", e);
		}
	}

	
	public void atualizar(Cliente cliente) throws ExcecaoDAO {

		if(conexao == null)
			throw new ExcecaoDAO("cliete_dao.conexao_nao_estabelecida");
		
		String sql = "UPDATE pizzaria.Cliente SET telefone = ?, nome = ?, endereco = ? WHERE id = ?";
		PreparedStatement stmt;
		
		try {
			stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, cliente.obterTelefone());
			stmt.setString(2, cliente.obterNome());
			stmt.setString(3, cliente.obterEndereco());
			stmt.setString(4, String.valueOf(cliente.obterId()));
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {

			throw new ExcecaoDAO("cliente_dao.nao_foi_possivel_atualizar_o_cliente", e);
		}
	}

	
	public Cliente buscar(String telefone) throws ExcecaoDAO, ExcecaoDeCliente {
		
		if(conexao == null)
			throw new ExcecaoDAO("cliete_dao.conexao_nao_estabelecida");
		
		Cliente cliente = null;

		String sql = "SELECT * FROM pizzaria.Cliente WHERE telefone = ?";
		PreparedStatement stmt;
		ResultSet rs;
		
		try {
			stmt = conexao.prepareStatement(sql);			
			stmt.setString(1, telefone);
			
			rs = stmt.executeQuery();
			
			if (rs.next()){
				cliente = Cliente.criarCliente(rs.getString("telefone"),rs.getString("nome"), rs.getString("endreceo"));
				cliente.definirId(rs.getLong("id"));
			}
			
		} catch (SQLException e) {

			throw new ExcecaoDAO("cliente_dao.nao_foi_possivel_atualizar_o_cliente", e);
		}
		
		return cliente;
	}
	
	
	public void excluir(Cliente cliente) throws ExcecaoDAO {
		
		if(conexao == null)
			throw new ExcecaoDAO("cliete_dao.conexao_nao_estabelecida");

		String sql = "DELETE FROM pizzaria.Cliente WHERE id = ?";
		PreparedStatement stmt;
		
		try {
			stmt = conexao.prepareStatement(sql);			
			stmt.setString(1, String.valueOf(cliente.obterId()));
			
			stmt.execute();
			
		} catch (SQLException e) {

			throw new ExcecaoDAO("cliente_dao.nao_foi_possivel_excluir_o_cliente", e);
		}
	}
	
	public void encerrarConexao() throws ExcecaoDAO{
		try {
			this.conexao.close();
		} catch (SQLException e) {
			throw new ExcecaoDAO("cliente_dao.nao_foi_possivel_encerrar_a_conexao");
		}
	}
}
