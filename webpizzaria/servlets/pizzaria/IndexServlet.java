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

import vo.PizzaVO;
import controlador.MantenedorDeRegistros;
import dominio.Pizza;
import excecoes.ExcecaoDAO;
import excecoes.ExcecaoDePizza;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/Index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
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
		// TODO Auto-generated method stub
		Collection<PizzaVO> pizzas = recuperarTodasAsPizzas(request, response);
		request.setAttribute("pizzas", pizzas);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
	}
	
	
	static Collection<PizzaVO> recuperarTodasAsPizzas(HttpServletRequest request, HttpServletResponse response) {
		Collection<PizzaVO> pizzasVO = null;
		
		try {
			Collection<Pizza> pizzas = MantenedorDeRegistros.obterInstancia().obterTodasAsPizzas();
			pizzasVO = new ArrayList<PizzaVO>();
			
			for(Pizza pizza: pizzas) {
				PizzaVO pizzaVO = new PizzaVO();
				
				pizzaVO.setId(pizza.obterId());
				pizzaVO.setNomePizza(pizza.obterNomePizza());
				pizzaVO.setIngredientes(pizza.obterIngredientes());
				pizzaVO.setPreco(pizza.obterPreco());
				
				pizzasVO.add(pizzaVO);
			}
		} catch (ExcecaoDAO | ExcecaoDePizza e) {
			// TODO Auto-generated catch block
			request.setAttribute("erro", true);
			request.setAttribute("mensagem", e.getMessage());
		}
		
		return pizzasVO;
	}

}
