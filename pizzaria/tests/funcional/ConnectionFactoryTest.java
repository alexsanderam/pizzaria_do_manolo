package funcional;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import pizzariaDAO.ConnectionFactory;
import excecoes.ExcecaoDAO;
 
public class ConnectionFactoryTest implements ConnectionFactory{
     
    private static ConnectionFactoryTest instancia;
     
    public static ConnectionFactoryTest obterInstancia(){
        if(instancia == null)
            return new ConnectionFactoryTest();
         
        return instancia;
    }
     
    public Connection obterConexao() throws ExcecaoDAO{
    	
    	String url = "jdbc:postgresql://localhost:5432/testpizzaria?searchpath=pizzaria";
        String usr = "postgres";
        String pass = "postgres";
         
		try {
			Class.forName("org.postgresql.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			e1.printStackTrace();
		} 
		
		Connection conexao = null;
		try {
			conexao = DriverManager.getConnection(url, usr, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conexao;
    }
 
}