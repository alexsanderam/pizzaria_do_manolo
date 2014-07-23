package pizzaria;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.MantenedorDeRegistros;
import dominio.Cliente;
import excecoes.ExcecaoDAO;
import excecoes.ExcecaoDeCliente;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Login() {
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
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		try {
			Cliente cliente = MantenedorDeRegistros.obterInstancia().obterClientePeloEmail(email);
			cliente.realizarAutenticacao(senha);
			request.getSession().setAttribute("cliente", cliente);
	        RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
	        rd.forward(request, response);
	        
		} catch (ExcecaoDAO | ExcecaoDeCliente e) {
			request.setAttribute("falhaNaAutenticacao", true);
			request.setAttribute("mensagem", e.getMessage());
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
	        rd.forward(request, response);
		}
	
	}

}
