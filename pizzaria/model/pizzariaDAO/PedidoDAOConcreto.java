package pizzariaDAO;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
import dominio.Pedido;
import excecoes.ExcecaoDAO;
 
public class PedidoDAOConcreto implements PedidoDAO{
 
    private Connection conexao = null;
     
    public PedidoDAOConcreto() throws ExcecaoDAO{
        this.conexao = criarConexao();
    }
     
     
    protected Connection criarConexao() throws ExcecaoDAO{
        return ConnectionFactoryConcreto.obterInstancia().obterConexao();
    }
     
    @Override
    public void incluir(Pedido pedido) throws ExcecaoDAO {
         
        if(conexao == null)
            throw new ExcecaoDAO("pedido_dao.conexao_nao_estabelecida");
         
        String sql = "INSERT INTO pizzaria.Pedido(id_cliente_fk, id_pizza_fk, data_hora, quantidade) values (?, ?, current_timestamp, ?)";
        PreparedStatement stmt;
         
        try {
            stmt = conexao.prepareStatement(sql);
 
            stmt.setLong(1, pedido.obterCliente().obterId());
            stmt.setLong(2, pedido.obterPizza().obterId());
            stmt.setInt(3, pedido.obterQuantidade());
             
            stmt.execute();
             
        } catch (Exception e) {
            throw new ExcecaoDAO("pedido_dao.nao_foi_possivel_incluir_o_pedido", e);
        }
    }
     
    @Override
    public void encerrarConexao() throws ExcecaoDAO{
        try {
            this.conexao.close();
        } catch (SQLException e) {
            throw new ExcecaoDAO("pedido_dao.nao_foi_possivel_encerrar_a_conexao");
        }
    }
     
}
