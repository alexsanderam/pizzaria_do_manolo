package pizzariaDAO;


import dominio.Cliente;
import excecoes.ExcecaoDAO;
import excecoes.ExcecaoDeCliente;

public interface ClienteDAO {
	
	public void incluir(Cliente cliente) throws ExcecaoDAO, ExcecaoDeCliente;
	public Cliente buscar(String telefone) throws ExcecaoDAO, ExcecaoDeCliente;
	public void encerrarConexao() throws ExcecaoDAO;
	
}
