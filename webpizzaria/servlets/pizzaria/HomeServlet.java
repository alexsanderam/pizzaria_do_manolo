package pizzaria;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ClienteVO;
import dominio.Cliente;

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
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
		
		ClienteVO clienteVO = new ClienteVO();
		clienteVO.setEmail(cliente.obterEmail());
		clienteVO.setEndereco(cliente.obterEndereco());
		clienteVO.setId(cliente.obterId());
		clienteVO.setTelefone(cliente.obterTelefone());
		clienteVO.setNome(cliente.obterNome());
		
		request.setAttribute("cliente", clienteVO);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/home.jsp");
        rd.forward(request, response);
	}

}
