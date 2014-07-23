package pizzariaDAO;

import java.util.Collection;

import dominio.ItemPedido;
import dominio.Pedido;
import dominio.Pizza;
import excecoes.ExcecaoDAO;
import excecoes.ExcecaoDeItemPedido;
import excecoes.ExcecaoDePizza;

public interface ItemPedidoDAO {

	public void incluir(ItemPedido itemPedido) throws ExcecaoDAO;
	public ItemPedido buscar(Pedido pedido, Pizza pizza) throws ExcecaoDAO, ExcecaoDeItemPedido;
	public Collection<ItemPedido> buscarTodosDoPedido(Pedido pedido) throws ExcecaoDAO, ExcecaoDeItemPedido, ExcecaoDePizza;
	public void encerrarConexao() throws ExcecaoDAO;	
	
}
