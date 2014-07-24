package pizzaria_test_case;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import pizzaria.Sistema;

public class MockSistema extends Sistema{
	
	
	public static void cadastrar_pizza() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conexao;
		Statement comandoSQL;
		
		String nome_pizza, ingredientes, preco;
		nome_pizza = "portuguse";
		ingredientes = "ovo, azeitona preta, calabresa, pimentão";
		preco = "2.50";
		String sql = "INSERT INTO CARDAPIO VALUES('"+nome_pizza+"', '"+ingredientes+"', '"+preco+"')";
		Class.forName("org.postgresql.Driver").newInstance();
		conexao = DriverManager.getConnection("jdbc:postgresql://192.168.122.199/pizza", "pizza", "pizza");
		comandoSQL = conexao.createStatement();
		comandoSQL.executeUpdate(sql);
		System.out.println("NOVA PIZZA INSERIDA COM SUCESSO!");
	}

}
