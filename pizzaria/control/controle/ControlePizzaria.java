package controle;

import controlador.ControladorDominio;
import controlador.MantenedorDeRegistros;
import dominio.Cliente;
import dominio.Pedido;
import dominio.Pizza;
import excecoes.ExcecaoDAO;
import excecoes.ExcecaoDeCliente;
import excecoes.ExcecaoDePedido;
import excecoes.ExcecaoDePizza;
import pizzaria.UIConcreto;
import pizzaria.UserInterface;
import vo.ClienteVO;
import vo.PizzaVO;

public class ControlePizzaria {

	private UserInterface ui;
	
	public ControlePizzaria(){
		ui = criarUI();
	}
	
	public void inicializar(){
		ui.inicializarUI();
	}
		
	private UserInterface criarUI(){
		return new UIConcreto(this);
	}
	
	public void acaoCadastrarCliente(){
		ClienteVO clienteVO = ui.capturarDadosDoCliente();
		casdastrarCliente(clienteVO);
		ui.exibirMensagem("CLIENTE INSERIDO COM SUCESSO!");	
	}
	
	public void acaoCadastrarPizza(){
		PizzaVO pizzaVO = ui.capturarDadosDaPizza();
		casdastrarPizza(pizzaVO);
		ui.exibirMensagem("NOVA PIZZA INSERIDA COM SUCESSO!");
	}
	
	public void acaoCadastrarPedido(){
		String telefoneDoCliente = null;
		String nomeDaPizza = null;
		Integer quantidade = null;
		Boolean confirmacaoDoPedido = null;
		
		
		try {
			telefoneDoCliente = ui.capturarTelefoneDoCliente();
			Cliente cliente = obterClientePeloTelefone(telefoneDoCliente);
			ui.exibirInformacoesDoCliente(clienteParaClienteVO(cliente));
			
			nomeDaPizza = ui.capturarNomeDaPizza();
			Pizza pizza = obterPizzaPeloNome(nomeDaPizza);
			ui.exibirInformcacoesDaPizza(pizzaParaPizzaVO(pizza));
			
			quantidade = ui.capturarQuantidadeDesejada();
			
			confirmacaoDoPedido = ui.solicitarConfirmacao();
			
			if(confirmacaoDoPedido == true){
				casdastrarPedido(cliente, pizza, quantidade);
				ui.exibirMensagem("PEDIDO INSERIDO COM SUCESSO!");
			}
			else
				ui.exibirMensagem("PEDIDO CANCELADO!");
			
		} catch (ExcecaoDAO | ExcecaoDePizza | ExcecaoDeCliente e) {
			ui.exibirMensagem(e.getMessage());
		}
	}
	
	
	private void casdastrarPizza(PizzaVO pizzaVO){		
		Pizza pizza = null;
		
		try {
			pizza = ControladorDominio.obterInstancia().novaPizza(pizzaVO.obterNomePizza(), pizzaVO.obterIngredientes(), pizzaVO.obterPreco());
			MantenedorDeRegistros.obterInstancia().incluirPizza(pizza);
			
		} catch (ExcecaoDAO | ExcecaoDePizza e1) {
			ui.exibirMensagem(e1.getMessage());
		}

	}
	
	private Pizza obterPizzaPeloNome(String nome) throws ExcecaoDAO, ExcecaoDePizza{
		Pizza pizza;

		pizza = MantenedorDeRegistros.obterInstancia().obterPizzaPorNome(nome);
	
		return pizza;
	}
	
	
	private void casdastrarCliente(ClienteVO clienteVO){		
		Cliente cliente = null;
		
		try {		
			cliente = ControladorDominio.obterInstancia().novoCliente(clienteVO.obterTelefone(), clienteVO.obterNome(), clienteVO.obterEndereco());
			
			MantenedorDeRegistros.obterInstancia().incluirCliente(cliente);	
			
		} catch (ExcecaoDeCliente | ExcecaoDAO e) {
			
			ui.exibirMensagem(e.getMessage());
		}
	}
	
	
	private Cliente obterClientePeloTelefone(String telefone) throws ExcecaoDAO, ExcecaoDeCliente{
		Cliente cliente;

		cliente = MantenedorDeRegistros.obterInstancia().obterClientePorTelefone(telefone);
		
		return cliente;
	}
	
	
	private void casdastrarPedido(Cliente cliente, Pizza pizza, Integer quantidade){	
		
		Pedido pedido = null;
		
		try {
			
			pedido = ControladorDominio.obterInstancia().novoPedido(cliente, pizza, null, quantidade);
			MantenedorDeRegistros.obterInstancia().incluirPedido(pedido);
			
		} catch (ExcecaoDePedido | ExcecaoDAO e) {
			ui.exibirMensagem(e.getMessage());
		}	
	}
	
	
	private ClienteVO clienteParaClienteVO(Cliente cliente){
		ClienteVO clienteVO = new ClienteVO(cliente.obterId(), cliente.obterTelefone(), cliente.obterNome(), cliente.obterEndereco());
		return clienteVO;
	}
	
	private PizzaVO pizzaParaPizzaVO(Pizza pizza){
		PizzaVO pizzaVO = new PizzaVO(pizza.obterId(), pizza.obterNomePizza(), pizza.obterIngredientes(), pizza.obterPreco());
		return pizzaVO;
	}
	
}
