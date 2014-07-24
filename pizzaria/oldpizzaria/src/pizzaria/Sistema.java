package pizzaria;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sistema {

	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			System.out.println("-----------------------");
			System.out.println("PIZZARIA DO MANOLO");
			System.out.println("-----------------------");
			System.out.println("[1] CADASTRAR NOVO CLIENTE");
			System.out.println("[2] CADASTRAR NOVO PEDIDO");
			System.out.println("[3] CADASTRAR NOVA PIZZA");
			String opcao = reader.readLine();
			System.out.println(opcao);
			if(opcao.equals("1")){
				cadastrar_cliente();
			}
			if(opcao.equals("2")){
				cadastrar_pedido();
			}
			
			if(opcao.equals("3")){
				cadastrar_pizza();
			}
			
		}
		
	}

	private static void cadastrar_pizza() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conexao;
		Statement comandoSQL;
		
		String nome_pizza, ingredientes, preco;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("NOME DA NOVA PIZZA: ");
		nome_pizza = reader.readLine();
		System.out.println("INGREDIENTES DA NOVA PIZZA: ");
		ingredientes = reader.readLine();
		System.out.println("PRECO DA NOVA PIZZA: ");
		preco = reader.readLine();
		String sql = "INSERT INTO CARDAPIO VALUES('"+nome_pizza+"', '"+ingredientes+"', '"+preco+"')";
		Class.forName("org.postgresql.Driver").newInstance();
		conexao = DriverManager.getConnection("jdbc:postgresql://192.168.122.199/pizza", "pizza", "pizza");
		comandoSQL = conexao.createStatement();
		comandoSQL.executeUpdate(sql);
		System.out.println("NOVA PIZZA INSERIDA COM SUCESSO!");
		System.in.read();

		
	}

	private static void cadastrar_pedido() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conexao;
		Statement comandoSQL;
		ResultSet resultado;
		
		String telefone, nome_pizza;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("TELEFONE DO CLIENTE: ");
		telefone = reader.readLine();
		Class.forName("org.postgresql.Driver").newInstance();
		conexao = DriverManager.getConnection("jdbc:postgresql://192.168.122.199/pizza", "pizza", "pizza");
		comandoSQL = conexao.createStatement();
		resultado = comandoSQL.executeQuery("SELECT * FROM CLIENTE WHERE TELEFONE = '"+telefone+"'");
		while (resultado.next()) {
			System.out.println("NOME: "+resultado.getString("NOME"));
			System.out.println("ENDERECO: "+resultado.getString("ENDERECO"));
		}
		resultado.close();
		System.out.println("PIZZA: ");
		nome_pizza = reader.readLine();
		comandoSQL = conexao.createStatement();
		resultado = comandoSQL.executeQuery("SELECT * FROM CARDAPIO WHERE NOME_PIZZA= '"+nome_pizza+"'");
		while (resultado.next()) {
			System.out.println("INGREDIENTES: "+resultado.getString("INGREDIENTES"));
			System.out.println("PRECO: "+resultado.getString("PRECO"));
		}
		
		System.out.println("CONFIRMA?[S/N]");
		String opcao = reader.readLine();
		if(opcao.equalsIgnoreCase("S")){
			System.out.println("QUANTIDADE DESEJADA: ");
			int quantidade = Integer.parseInt(reader.readLine());
			comandoSQL.executeUpdate("INSERT INTO PEDIDO VALUES('"+telefone+"', current_timestamp, '"+nome_pizza+"', "+quantidade+")");
			System.out.println("PEDIDO INSERIDO COM SUCESSO!");
			System.in.read();
		}	
	}
	

	private static void cadastrar_cliente() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conexao;
		Statement comandoSQL;
		
		String telefone, nome, endereco;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("TELEFONE DO NOVO CLIENTE: ");
		telefone = reader.readLine();
		System.out.println("NOME DO NOVO CLIENTE: ");
		nome = reader.readLine();
		System.out.println("ENDERECO DO NOVO CLIENTE: ");
		endereco = reader.readLine();
		String sql = "INSERT INTO CLIENTE VALUES('"+telefone+"', '"+nome+"', '"+endereco+"')";
		Class.forName("org.postgresql.Driver").newInstance();
		conexao = DriverManager.getConnection("jdbc:postgresql://192.168.122.199/pizza", "pizza", "pizza");
		comandoSQL = conexao.createStatement();
		comandoSQL.executeUpdate(sql);
		System.out.println("CLIENTE INSERIDO COM SUCESSO!");
		System.in.read();
	}

	
}
