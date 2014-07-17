package pizzariaDAO;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import dominio.Cliente;
import excecoes.ExcecaoDAO;
import excecoes.ExcecaoDeCliente;
 
public class ClienteDAOConcreto implements ClienteDAO{
     
    private Connection conexao = null;
     
     
    public ClienteDAOConcreto() throws ExcecaoDAO{
        this.conexao = criarConexao();
    }
     
    protected Connection criarConexao() throws ExcecaoDAO{
        return ConnectionFactoryConcreto.obterInstancia().obterConexao();
    }
     
    @Override
    public void incluir(Cliente cliente) throws ExcecaoDAO, ExcecaoDeCliente {
         
        if(conexao == null)
            throw new ExcecaoDAO("cliete_dao.conexao_nao_estabelecida");
         
        String sql = "INSERT INTO pizzaria.Cliente(telefone, nome, endereco) values (?, ?, ?) RETURNING id";
        PreparedStatement stmt;
        ResultSet rs;
         
        try {
            stmt = conexao.prepareStatement(sql);
             
            stmt.setString(1, cliente.obterTelefone());
            stmt.setString(2, cliente.obterNome());
            stmt.setString(3, cliente.obterEndereco());
 
            rs = stmt.executeQuery();
             
            if (rs.next()){
                cliente.definirId(rs.getLong("id"));
            }
             
             
        } catch (SQLException e) {
 
            throw new ExcecaoDAO("cliente_dao.nao_foi_possivel_incluir_o_cliente", e);
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
                cliente = Cliente.criarCliente(rs.getString("telefone"),rs.getString("nome"), rs.getString("endereco"));
                cliente.definirId(rs.getLong("id"));
            }
             
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExcecaoDAO("cliente_dao.nao_foi_possivel_localizar_o_cliente", e);
        }
        
        if(cliente == null)
        	throw new ExcecaoDAO("cliente_dao.nao_foi_possivel_localizar_o_cliente");
        
        
        return cliente;
    }
     
    @Override
    public void encerrarConexao() throws ExcecaoDAO{
        try {
            this.conexao.close();
        } catch (SQLException e) {
            throw new ExcecaoDAO("cliente_dao.nao_foi_possivel_encerrar_a_conexao");
        }
    }
}