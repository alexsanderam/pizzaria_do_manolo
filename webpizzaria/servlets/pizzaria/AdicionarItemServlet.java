package pizzaria;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.MantenedorDeRegistros;
import dominio.Pizza;
import excecoes.ExcecaoDAO;
import excecoes.ExcecaoDePizza;
import vo.ItemPedidoVO;
import vo.PedidoVO;
import vo.PizzaVO;

/**
 * Servlet implementation class AdicionarItemServlet
 */
@WebServlet("/AdicionarItem")
public class AdicionarItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdicionarItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idPizzaString = (String) request.getParameter("idPizza");
		String quantidadeString = (String) request.getParameter("quantidade");
		
		Long idPizza = Long.valueOf(idPizzaString);	
		Integer quantidade = Integer.valueOf(quantidadeString);
	
		ItemPedidoVO itemPedido = new ItemPedidoVO(obterPizza(idPizza), quantidade);
		
		PedidoVO novoPedido = (PedidoVO) request.getSession().getAttribute("novoPedido");
		novoPedido.getItensDoPedido().add(itemPedido);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Home");
        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<PizzaVO> pizzas = IndexServlet.recuperarTodasAsPizzas(request, response);
		request.setAttribute("pizzas", pizzas);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/adicionar-item.jsp");
        rd.forward(request, response);
	}
	
	private PizzaVO obterPizza(Long id) {
		Pizza pizza;
		PizzaVO pizzaVO = null;
		try {
			pizza = MantenedorDeRegistros.obterInstancia().obterPizzaPeloIdentificador(id);
			pizzaVO = new PizzaVO();
			pizzaVO.setId(id);
			pizzaVO.setNomePizza(pizza.obterNomePizza());
			pizzaVO.setIngredientes(pizza.obterIngredientes());
			pizzaVO.setPreco(pizza.obterPreco());
		} catch (ExcecaoDAO | ExcecaoDePizza e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pizzaVO;
		
	}

}
