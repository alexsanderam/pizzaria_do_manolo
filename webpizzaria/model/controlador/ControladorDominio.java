package controlador;


import dominio.Cliente;
import dominio.Pedido;
import dominio.Pizza;
import excecoes.ExcecaoDeCliente;
import excecoes.ExcecaoDePedido;
import excecoes.ExcecaoDePizza;

public class ControladorDominio {

	private static ControladorDominio instancia;
	
	private ControladorDominio(){
		
	}

	public static ControladorDominio obterInstancia(){
		if(instancia == null)
			return new ControladorDominio();
		
		return instancia;
	}
	
	public Cliente novoCliente(String telefone, String email, String senha, String nome, String endereco) throws ExcecaoDeCliente{
		return Cliente.criarCliente(telefone, email, senha, nome, endereco);
	}
	
	public Pizza novaPizza(String nomePizza, String ingredientes, Float preco) throws ExcecaoDePizza{
		return Pizza.criarPizza(nomePizza, ingredientes, preco);
	}
	
	public Pedido novoPedido(Cliente cliente) throws ExcecaoDePedido{
		return Pedido.novoPedido(cliente);
	}	
}
