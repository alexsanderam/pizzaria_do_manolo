package pizzaria;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.MantenedorDeRegistros;
import vo.ClienteVO;
import vo.ItemPedidoVO;
import vo.PedidoVO;
import vo.PizzaVO;
import dominio.Cliente;
import dominio.EnumFormaDePagamento;
import dominio.ItemPedido;
import dominio.Pedido;
import dominio.Pizza;
import excecoes.ExcecaoDAO;
import excecoes.ExcecaoDeCliente;
import excecoes.ExcecaoDePedido;
/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		ClienteVO cliente = recuperarClienteLogado(request, response);
		request.setAttribute("cliente", cliente);		
		
		Collection<PedidoVO> pedidos = recuperarHistoricoDePedidos(request, response);
		request.setAttribute("pedidos", pedidos);
		request.setAttribute("formaDePagamentoEnum", EnumFormaDePagamento.DINHEIRO_SEM_TROCO);
		
		Pedido novoPedido = (Pedido) request.getSession().getAttribute("novoPedido");
		try {
			PedidoVO novoPedidoVO = obterVODePedido(novoPedido);
			request.setAttribute("novoPedido", novoPedidoVO);
		} catch (ExcecaoDAO | ExcecaoDeCliente e) {
			// TODO Auto-generated catch block
			request.setAttribute("erro", true);
			request.setAttribute("mensagem", e.getMessage());
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/home.jsp");
        rd.forward(request, response);
		
		
	}

	private Collection<PedidoVO> recuperarHistoricoDePedidos(HttpServletRequest request, HttpServletResponse response)  {
		Collection<PedidoVO> pedidosVO = new ArrayList<PedidoVO>();
		Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
		try {
			Collection<Pedido> pedidos = MantenedorDeRegistros.obterInstancia().obterTodosPedidosDoClienteDescendentemente(cliente);
			
			for (Pedido pedido: pedidos) {
				PedidoVO pedidoVO = obterVODePedido(pedido);
				pedidosVO.add(pedidoVO);
			}
			
		} catch (ExcecaoDAO | ExcecaoDePedido | ExcecaoDeCliente e) {
			// TODO Auto-generated catch block
			request.setAttribute("erro", true);
			request.setAttribute("mensagem", e.getMessage());
		}
		
		
		return pedidosVO;
	}
	
	private PedidoVO obterVODePedido(Pedido pedido) throws ExcecaoDAO, ExcecaoDeCliente {
		PedidoVO pedidoVO = new PedidoVO();
		if (pedido != null) {
			Cliente cliente = MantenedorDeRegistros.obterInstancia().obterClientePeloIdentificador(pedido.obterCliente().obterId());
			pedidoVO.setClienteVO(obterVODeCliente(cliente));
			pedidoVO.setItensDoPedido(recuperarItensPedido(pedido));
			pedidoVO.setDataHora(pedido.obterDataHora());
			pedidoVO.setTotal(pedido.obterValorTotal());
		} else {
			pedidoVO.setItensDoPedido(new ArrayList<ItemPedidoVO>());
		}
		
		return pedidoVO;
	}
	
	private Collection<ItemPedidoVO> recuperarItensPedido(Pedido pedido) {
		Collection<ItemPedido> itensPedido = pedido.obterItens();
		Collection<ItemPedidoVO> itensPedidoVO = new ArrayList<ItemPedidoVO>();
		for (ItemPedido itemPedido: itensPedido) {
			ItemPedidoVO itemPedidoVO = new ItemPedidoVO();
			itemPedidoVO.setPizza(obterVODePizza(itemPedido.obterPizza()));
			itemPedidoVO.setQuantidade(itemPedido.obterQuantidade());
			itemPedidoVO.setSubtotal(itemPedido.obterSubtotal());
			itensPedidoVO.add(itemPedidoVO);
		}
		return itensPedidoVO;
	}
	
	
	private PizzaVO obterVODePizza(Pizza pizza) {
		PizzaVO pizzaVO = new PizzaVO();
		pizzaVO.setId(pizza.obterId());
		pizzaVO.setNomePizza(pizza.obterNomePizza());
		pizzaVO.setIngredientes(pizza.obterIngredientes());
		pizzaVO.setPreco(pizza.obterPreco());
		return pizzaVO;
	}
	static ClienteVO recuperarClienteLogado(HttpServletRequest request, HttpServletResponse response) {
		Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
		ClienteVO clienteVO = obterVODeCliente(cliente);
		
		return clienteVO;
	}
	
	private static ClienteVO obterVODeCliente(Cliente cliente) {
		ClienteVO clienteVO = new ClienteVO();
		if (cliente != null) {
			clienteVO.setEmail(cliente.obterEmail());
			clienteVO.setEndereco(cliente.obterEndereco());
			clienteVO.setId(cliente.obterId());
			clienteVO.setTelefone(cliente.obterTelefone());
			clienteVO.setNome(cliente.obterNome());
		}
		return clienteVO;
	}

}
