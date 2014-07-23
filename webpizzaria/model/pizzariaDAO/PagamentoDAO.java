package pizzariaDAO;

import dominio.Pagamento;
import excecoes.ExcecaoDAO;
import excecoes.ExcecaoDePagamento;

public interface PagamentoDAO {

	public void incluir(Pagamento pagamento) throws ExcecaoDAO, ExcecaoDePagamento;
	public Pagamento buscar(Long id) throws ExcecaoDAO, ExcecaoDePagamento;
	public void encerrarConexao() throws ExcecaoDAO;	
}
