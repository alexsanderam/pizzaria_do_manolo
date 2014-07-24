package controlador;

import java.util.Collection;

import pizzariaDAO.ClienteDAO;
import pizzariaDAO.ClienteDAOConcreto;
import pizzariaDAO.ItemPedidoDAO;
import pizzariaDAO.ItemPedidoDAOConcreto;
import pizzariaDAO.PagamentoDAO;
import pizzariaDAO.PagamentoDAOConcreto;
import pizzariaDAO.PedidoDAO;
import pizzariaDAO.PedidoDAOConcreto;
import pizzariaDAO.PizzaDAO;
import pizzariaDAO.PizzaDAOConcreto;
import dominio.Cliente;
import dominio.Pagamento;
import dominio.Pedido;
import dominio.Pizza;
import excecoes.ExcecaoDAO;
import excecoes.ExcecaoDeCliente;
import excecoes.ExcecaoDePagamento;
import excecoes.ExcecaoDePedido;
import excecoes.ExcecaoDePizza;

public class MantenedorDeRegistros {
	
	private static MantenedorDeRegistros instancia;

	public static MantenedorDeRegistros obterInstancia(){
		if(instancia == null)
			return new MantenedorDeRegistros();
		
		return instancia;
	}
	
	protected ClienteDAO criarClienteDAO() throws ExcecaoDAO{
		return new ClienteDAOConcreto();
	}
	
	protected PizzaDAO criarPizzaDAO() throws ExcecaoDAO{
		return new PizzaDAOConcreto();
	}
	
	protected ItemPedidoDAO criarItemPedidoDAO() throws ExcecaoDAO {
		return new ItemPedidoDAOConcreto();
	}
	
	protected PedidoDAO criarPedidoDAO() throws ExcecaoDAO{
		return new PedidoDAOConcreto();
	}

	protected PagamentoDAO criarPagamentoDAO() throws ExcecaoDAO{
		return new PagamentoDAOConcreto();
	}
	
	
	public void incluirCliente(Cliente cliente) throws ExcecaoDAO, ExcecaoDeCliente{
		ClienteDAO clienteDAO = criarClienteDAO();
		clienteDAO.incluir(cliente);
		clienteDAO.encerrarConexao();
	}
	
	public Cliente obterClientePeloEmail(String email) throws ExcecaoDAO, ExcecaoDeCliente{
		ClienteDAO clienteDAO = criarClienteDAO();
		Cliente cliente = clienteDAO.buscar(email);
		clienteDAO.encerrarConexao();
		return cliente;
	}
	
	public Cliente obterClientePeloIdentificador(Long id) throws ExcecaoDAO, ExcecaoDeCliente{
		ClienteDAO clienteDAO = criarClienteDAO();
		Cliente cliente = clienteDAO.buscar(id);
		clienteDAO.encerrarConexao();
		return cliente;
	}
	
	public Pizza obterPizzaPeloNome(String nome) throws ExcecaoDAO, ExcecaoDePizza{
		PizzaDAO pizzaDAO = criarPizzaDAO();
		Pizza pizza = pizzaDAO.buscar(nome);
		pizzaDAO.encerrarConexao();
		return pizza;
	}
	
	public Pizza obterPizzaPeloIdentificador(Long id) throws ExcecaoDAO, ExcecaoDePizza{
		PizzaDAO pizzaDAO = criarPizzaDAO();
		Pizza pizza = pizzaDAO.buscar(id);
		pizzaDAO.encerrarConexao();
		return pizza;
	}
	
	public Collection<Pizza> obterTodasAsPizzas() throws ExcecaoDAO, ExcecaoDePizza{
		PizzaDAO pizzaDAO = criarPizzaDAO();
		Collection<Pizza> pizzas = pizzaDAO.buscar();
		pizzaDAO.encerrarConexao();
		return pizzas;
	}

	public void incluirPizza(Pizza pizza) throws ExcecaoDAO, ExcecaoDePizza{
		PizzaDAO pizzaDAO = criarPizzaDAO();
		pizzaDAO.incluir(pizza);
		pizzaDAO.encerrarConexao();
	}
	
	public void incluirPedido(Pedido pedido, Pagamento pagamento) throws ExcecaoDAO, ExcecaoDePagamento, ExcecaoDePedido{
		PedidoDAO pedidoDAO  = criarPedidoDAO();
		PagamentoDAO pagamentoDAO = criarPagamentoDAO();
		pagamentoDAO.incluir(pagamento);
		
		/*Associa o pagamento ao pedido*/
		pedido.definirPagamento(pagamento);

		
		pedidoDAO.incluir(pedido);
		pedidoDAO.encerrarConexao();
	}

	public Pedido obterPedidoPorIdentificador(Long id) throws ExcecaoDAO, ExcecaoDePedido{
		Pedido pedido;
			
		PedidoDAO pedidoDAO  = criarPedidoDAO();
		pedido = pedidoDAO.buscar(id);
		pedidoDAO.encerrarConexao();
		
		return pedido;
	}
	
	
	public Collection<Pedido> obterTodosPedidosDoClienteDescendentemente(Cliente cliente) throws ExcecaoDAO, ExcecaoDePedido{
		Collection<Pedido> pedidos = null;
			
		PedidoDAO pedidoDAO  = criarPedidoDAO();
		pedidos = pedidoDAO.buscarTodosPedidosDoCliente(cliente);
		pedidoDAO.encerrarConexao();
		
		return pedidos;
	}
}
