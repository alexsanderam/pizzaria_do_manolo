package pizzaria;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.ControladorDominio;
import controlador.MantenedorDeRegistros;
import dominio.Cliente;
import dominio.EnumFormaDePagamento;
import dominio.Pagamento;
import dominio.Pedido;
import dominio.Pizza;
import excecoes.ExcecaoDAO;
import excecoes.ExcecaoDeItemPedido;
import excecoes.ExcecaoDePagamento;
import excecoes.ExcecaoDePedido;
import excecoes.ExcecaoDePizza;
import vo.PizzaVO;

/**
 * Servlet implementation class AdicionarItemServlet
 */
@WebServlet("/Pedido")
public class PedidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PedidoServlet() {
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
		String action = request.getParameter("action");
		
		if (action != null && action.equals("adicionarItem")) {
			adicionarItens(request, response);
		} else if (action != null && action.equals("fecharPedido")) {
			fecharPedido(request, response);
		} else {
			recuperarTodasPizzas(request, response);
		}
		
		
	}
	
	private void recuperarTodasPizzas(HttpServletRequest request,
			HttpServletResponse response) {
		Collection<PizzaVO> pizzas = IndexServlet.recuperarTodasAsPizzas(request, response);
		request.setAttribute("pizzas", pizzas);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/adicionar-item.jsp");
        try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			request.setAttribute("erro", true);
			request.setAttribute("mensagem", e.getMessage());
		}
		
	}

	private void fecharPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Pedido novoPedido = (Pedido) request.getSession().getAttribute("novoPedido");
		String formaDePagamentoString = request.getParameter("formaDePagamento");
		String valorRecebidoString = request.getParameter("valorRecebido");
		EnumFormaDePagamento formaDePagamento = EnumFormaDePagamento.valueOf(formaDePagamentoString);
		Float valorRecebido = novoPedido.obterValorTotal();
		try {
			valorRecebido = Float.valueOf(valorRecebidoString);
		} catch (NumberFormatException e) {
			if (formaDePagamento.equals(EnumFormaDePagamento.DINHEIRO_COM_TROCO)) {
				request.setAttribute("erro", true);
				request.setAttribute("mensagem", "valor recebido inválido");
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Home");
		        rd.forward(request, response);
			} 		
		}
		
		Pagamento pagamento;
		try {
			pagamento = ControladorDominio.obterInstancia().novoPagamento(formaDePagamento, valorRecebido);
			MantenedorDeRegistros.obterInstancia().incluirPedido(novoPedido, pagamento);
			request.getSession().setAttribute("novoPedido", null);
		} catch (ExcecaoDePagamento | ExcecaoDAO | ExcecaoDePedido e) {
			// TODO Auto-generated catch block
			request.setAttribute("erro", true);
			request.setAttribute("mensagem", e.getMessage());
		} finally {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Home");
	        rd.forward(request, response);
		}
		
	}
	
	private void adicionarItens(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idPizzaString = (String) request.getParameter("idPizza");
		String quantidadeString = (String) request.getParameter("quantidade");
		
		if (idPizzaString != null && quantidadeString != null) {
			try {
				Long idPizza = Long.valueOf(idPizzaString);	
				Integer quantidade = Integer.valueOf(quantidadeString);
			
				Pedido novoPedido = (Pedido) request.getSession().getAttribute("novoPedido");
				Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
				
				if (novoPedido == null) {	
					novoPedido = ControladorDominio.obterInstancia().novoPedido(cliente);
				}
				
				Pizza pizza = MantenedorDeRegistros.obterInstancia().obterPizzaPeloIdentificador(idPizza);
				novoPedido.definirCliente(cliente);
				novoPedido.incluirPizza(pizza, quantidade);

				request.getSession().setAttribute("novoPedido", novoPedido);
				
				
			} catch (NumberFormatException | ExcecaoDAO | ExcecaoDePizza | ExcecaoDePedido | ExcecaoDeItemPedido e) {
				request.setAttribute("erro", true);
				request.setAttribute("mensagem", e.getMessage());
			} finally {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Home");
		        rd.forward(request, response);
			}
			
			
		}
	}
}


