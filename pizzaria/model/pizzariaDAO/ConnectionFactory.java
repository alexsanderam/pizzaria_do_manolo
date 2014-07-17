package pizzariaDAO;

import java.sql.Connection;

import excecoes.ExcecaoDAO;

public interface ConnectionFactory {

	public Connection obterConexao() throws ExcecaoDAO;
	
}
