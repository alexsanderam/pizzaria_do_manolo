package pizzariaDAO;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import dominio.Pizza;
import excecoes.ExcecaoDAO;
import excecoes.ExcecaoDePizza;
 
public class PizzaDAOConcreto implements PizzaDAO{
     
    private Connection conexao;
     
     
    public PizzaDAOConcreto() throws ExcecaoDAO {
        conexao = criarConexao();
    }
     
     
    protected Connection criarConexao() throws ExcecaoDAO{
        return ConnectionFactoryConcreto.obterInstancia().obterConexao();
    }
     
 
    @Override
    public void incluir(Pizza pizza) throws ExcecaoDAO, ExcecaoDePizza {
        if(conexao == null)
            throw new ExcecaoDAO("pizza_dao.conexao_nao_estabelecida");
         
        String sql = "INSERT INTO webpizzaria.Pizza(nome, ingredientes, preco) values (?, ?, ?) RETURNING id;";
        PreparedStatement stmt;
        ResultSet rs;
         
        try {
            stmt = conexao.prepareStatement(sql);
             
            stmt.setString(1, pizza.obterNomePizza());
            stmt.setString(2, pizza.obterIngredientes());
            stmt.setFloat(3, pizza.obterPreco());
             
            rs = stmt.executeQuery();
             
            if (rs.next()){
                pizza.definirId(rs.getLong("id"));
            }
             
        } catch (SQLException e) {
 
            throw new ExcecaoDAO("pizza_dao.nao_foi_possivel_incluir_a_pizza", e);
        }
        
    }
     
     
    public Pizza buscar(String nome) throws ExcecaoDAO, ExcecaoDePizza {
         
        if(conexao == null)
            throw new ExcecaoDAO("pizza_dao.conexao_nao_estabelecida");
         
        Pizza pizza = null;
 
        String sql = "SELECT ingredientes, preco, id FROM webpizzaria.Pizza WHERE nome = ?";
        PreparedStatement stmt;
        ResultSet rs;
         
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
             
            rs = stmt.executeQuery();
             
            if (rs.next()){
            	String ingredientes = rs.getString("ingredientes");
            	Float preco = rs.getFloat("preco");
            	
                pizza = Pizza.criarPizza(nome, ingredientes, preco);
                pizza.definirId(rs.getLong("id"));
            }
             
        } catch (SQLException e) {
 
            throw new ExcecaoDAO("pizza_dao.nao_foi_possivel_localizar_a_pizza", e);
        }
        
        if(pizza == null)
        	throw new ExcecaoDAO("pizza_dao.nao_foi_possivel_localizar_a_pizza");
         
        return pizza;
    }
    
	@Override
	public Pizza buscar(Long id) throws ExcecaoDAO, ExcecaoDePizza {
        if(conexao == null)
            throw new ExcecaoDAO("pizza_dao.conexao_nao_estabelecida");
         
        Pizza pizza = null;
 
        String sql = "SELECT nome, ingredientes, preco  FROM webpizzaria.Pizza WHERE id = ?";
        PreparedStatement stmt;
        ResultSet rs;
         
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);
             
            rs = stmt.executeQuery();
             
            if (rs.next()){
            	String nome = rs.getString("nome");
            	String ingredientes = rs.getString("ingredientes");
            	Float preco = rs.getFloat("preco");
            	
                pizza = Pizza.criarPizza(nome, ingredientes, preco);
                pizza.definirId(id);
            }
             
        } catch (SQLException e) {
 
            throw new ExcecaoDAO("pizza_dao.nao_foi_possivel_localizar_a_pizza", e);
        }
        
        if(pizza == null)
        	throw new ExcecaoDAO("pizza_dao.nao_foi_possivel_localizar_a_pizza");
         
        return pizza;
	}
	
	@Override
	public Collection<Pizza> buscar() throws ExcecaoDAO {
		if(conexao == null)
            throw new ExcecaoDAO("pizza_dao.conexao_nao_estabelecida");
         
        Pizza pizza = null;
        Collection<Pizza> pizzas = new ArrayList<Pizza>();
 
        String sql = "SELECT id, nome, ingredientes, preco  FROM webpizzaria.Pizza";
        PreparedStatement stmt = null;
        ResultSet rs;
         
        try {
            stmt = conexao.prepareStatement(sql);   
            rs = stmt.executeQuery();
             
            while (rs.next()){
            	Long id = rs.getLong("id");
            	String nome = rs.getString("nome");
            	String ingredientes = rs.getString("ingredientes");
            	Float preco = rs.getFloat("preco");
            	
                pizza = Pizza.criarPizza(nome, ingredientes, preco);
                pizza.definirId(id);
                pizzas.add(pizza);
            }
             
        } catch (SQLException | ExcecaoDePizza e) {
 
            throw new ExcecaoDAO("pizza_dao.nao_foi_possivel_localizar_a_pizza", e);
        }
        
        if(pizza == null)
        	throw new ExcecaoDAO("pizza_dao.nao_foi_possivel_localizar_a_pizza");
         
        return pizzas;
	}
     
 
    @Override
    public void encerrarConexao() throws ExcecaoDAO {
        try {
            this.conexao.close();
        } catch (SQLException e) {
            throw new ExcecaoDAO("pizza_dao.nao_foi_possivel_encerrar_a_conexao");
        }  
    }
 
}