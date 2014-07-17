package pizzariaDAO;

import dominio.Pedido;
import excecoes.ExcecaoDAO;

public interface PedidoDAO {

	public void incluir(Pedido pedido) throws ExcecaoDAO;
	public void encerrarConexao() throws ExcecaoDAO;
	
}
