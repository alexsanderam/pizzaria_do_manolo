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
import dominio.ItemPedido;
import dominio.Pedido;
import excecoes.ExcecaoDAO;
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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		ClienteVO cliente = recuperarClienteLogado(request, response);
		request.setAttribute("cliente", cliente);		
		
		Collection<PedidoVO> pedidos = recuperarHistoricoDePedidos(request, response);
		request.setAttribute("pedidos", pedidos);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/home.jsp");
        rd.forward(request, response);
	}

	private Collection<PedidoVO> recuperarHistoricoDePedidos(HttpServletRequest request, HttpServletResponse response)  {
		Collection<PedidoVO> pedidosVO = new ArrayList<PedidoVO>();
		Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
		try {
			Collection<Pedido> pedidos = MantenedorDeRegistros.obterInstancia().obterTodosPedidosDoClienteDescendentemente(cliente);
			
			for (Pedido pedido: pedidos) {
				PedidoVO pedidoVO = new PedidoVO();
				pedidoVO.setItensDoPedido(recuperarItensPedido(pedido));
				pedidoVO.setDataHora(pedido.obterDataHora());
				pedidosVO.add(pedidoVO);
			}
		} catch (ExcecaoDAO | ExcecaoDePedido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return pedidosVO;
	}
	
	private Collection<ItemPedidoVO> recuperarItensPedido(Pedido pedido) {
		Collection<ItemPedido> itensPedido = pedido.obterItens();
		Collection<ItemPedidoVO> itensPedidoVO = new ArrayList<ItemPedidoVO>();
		for (ItemPedido itemPedido: itensPedido) {
			ItemPedidoVO itemPedidoVO = new ItemPedidoVO();
			PizzaVO pizzaVO = new PizzaVO();
			pizzaVO.setNomePizza(itemPedido.obterPizza().obterNomePizza());
			itemPedidoVO.setPizza(pizzaVO);
			itemPedidoVO.setQuantidade(itemPedido.obterQuantidade());
			
			itensPedidoVO.add(itemPedidoVO);
		}
		return itensPedidoVO;
	}
	
	private ClienteVO recuperarClienteLogado(HttpServletRequest request, HttpServletResponse response) {
		Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
		ClienteVO clienteVO = new ClienteVO();
		clienteVO.setEmail(cliente.obterEmail());
		clienteVO.setEndereco(cliente.obterEndereco());
		clienteVO.setId(cliente.obterId());
		clienteVO.setTelefone(cliente.obterTelefone());
		clienteVO.setNome(cliente.obterNome());
		
		return clienteVO;
	}

}
