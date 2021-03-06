package pizzariaDAO;

import java.util.Collection;

import dominio.Pizza;
import excecoes.ExcecaoDAO;
import excecoes.ExcecaoDePizza;

public interface PizzaDAO {
	
	public void incluir(Pizza pizza) throws ExcecaoDAO, ExcecaoDePizza;
	public Pizza buscar(String nome) throws ExcecaoDAO, ExcecaoDePizza;
	public Pizza buscar(Long id) throws ExcecaoDAO, ExcecaoDePizza;
	public void encerrarConexao() throws ExcecaoDAO;
	public Collection<Pizza> buscar() throws ExcecaoDAO;

}
