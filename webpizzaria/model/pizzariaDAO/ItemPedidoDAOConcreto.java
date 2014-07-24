package pizzariaDAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import dominio.ItemPedido;
import dominio.Pedido;
import dominio.Pizza;
import excecoes.ExcecaoDAO;
import excecoes.ExcecaoDeItemPedido;
import excecoes.ExcecaoDePizza;


public class ItemPedidoDAOConcreto implements ItemPedidoDAO{
	
	protected Connection conexao;
    
    public ItemPedidoDAOConcreto() throws ExcecaoDAO {
        conexao = criarConexao();
    }
     
     
    protected Connection criarConexao() throws ExcecaoDAO{
        return ConnectionFactoryConcreto.obterInstancia().obterConexao();
    }

	@Override
	public void incluir(ItemPedido itemPedido) throws ExcecaoDAO {
	       if(conexao == null)
	            throw new ExcecaoDAO("itempedido_dao.conexao_nao_estabelecida");
	         
	        String sql = "INSERT INTO webpizzaria.ItemPedido(id_pedido_fk, id_pizza_fk, quantidade) values (?, ?, ?)";
	        PreparedStatement stmt;
	         
	        try {
	            stmt = conexao.prepareStatement(sql);
	             
	            stmt.setLong(1, itemPedido.obterPedido().obterId());
	            stmt.setLong(2, itemPedido.obterPizza().obterId());
	            stmt.setInt(3, itemPedido.obterQuantidade());
	            
	            stmt.execute();

	        } catch (SQLException e) {
	 
	            throw new ExcecaoDAO("itempedido_dao.nao_foi_possivel_incluir_o_item_do_pedido", e);
	        }
	}

	@Override
	public ItemPedido buscar(Pedido pedido, Pizza pizza) throws ExcecaoDAO,	ExcecaoDeItemPedido {
	       if(conexao == null)
	            throw new ExcecaoDAO("itempedido_dao.conexao_nao_estabelecida");
	         
	        ItemPedido itemPedido = null;
	        Long id_pedido = pedido.obterId();
	        Long id_pizza = pizza.obterId();
	 
	        String sql = "SELECT quantidade FROM webpizzaria.ItemPedido WHERE id_pedido_fk = ? AND id_pizza_fk = ?";
	        PreparedStatement stmt;
	        ResultSet rs;
	         
	        try {
	            stmt = conexao.prepareStatement(sql);
	            stmt.setLong(1, id_pedido);
	            stmt.setLong(1, id_pizza);
	             
	            rs = stmt.executeQuery();
	             
	            if (rs.next()){
	            	Integer quantidade = rs.getInt("quantidade");
	            	itemPedido = ItemPedido.criarItemPedido(pedido, pizza, quantidade);
	            }
	             
	        } catch (SQLException e) {
	 
	            throw new ExcecaoDAO("itempedido_dao.nao_foi_possivel_localizar_o_item_de_pedido", e);
	        }
	        
	        if(itemPedido == null)
	        	throw new ExcecaoDAO("itempedido_dao.nao_foi_possivel_localizar_o_item_de_pedido");
	         
	        return itemPedido;
	}

	@Override
	public Collection<ItemPedido> buscarTodosDoPedido(Pedido pedido) throws ExcecaoDAO, ExcecaoDeItemPedido, ExcecaoDePizza {
	       if(conexao == null)
	            throw new ExcecaoDAO("itempedido_dao.conexao_nao_estabelecida");
	         
	        Collection<ItemPedido> itensDoPedido = null;
	        Long id_pedido = pedido.obterId();
	 
	        String sql = "SELECT id_pizza_fk, quantidade FROM webpizzaria.ItemPedido WHERE id_pedido_fk = ?";
	        PreparedStatement stmt;
	        ResultSet rs;
	         
	        try {
	            stmt = conexao.prepareStatement(sql);
	            stmt.setLong(1, id_pedido);
	             
	            rs = stmt.executeQuery();
	            
	            itensDoPedido = new ArrayList<ItemPedido>();
	            PizzaDAO pizzaDAO = new PizzaDAOConcreto();
	             
	            while (rs.next()){
	            	Long id_pizza = rs.getLong("id_pizza_fk");
	            	Pizza pizza = pizzaDAO.buscar(id_pizza);
	            	
	            	Integer quantidade = rs.getInt("quantidade");
	            	
	            	ItemPedido itemPedido = ItemPedido.criarItemPedido(pedido, pizza, quantidade);
	            	
	            	itensDoPedido.add(itemPedido);
	            }
	            
	            pizzaDAO.encerrarConexao();
	             
	        } catch (SQLException e) {
	 
	            throw new ExcecaoDAO("itempedido_dao.nao_foi_possivel_localizar_o_item_de_pedido", e);
	        }

	         
	        return itensDoPedido;
	}

    @Override
    public void encerrarConexao() throws ExcecaoDAO {
        try {
            this.conexao.close();
        } catch (SQLException e) {
            throw new ExcecaoDAO("itempedido_dao.nao_foi_possivel_encerrar_a_conexao");
        }  
    }

}
