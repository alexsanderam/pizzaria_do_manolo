package pizzaria;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import vo.ClienteVO;
import vo.PizzaVO;
import controle.ControlePizzaria;

public class UIConcreto implements UserInterface{
	
	private ControlePizzaria controlePizzaria;
	
	private static final Integer CADASTRAR_CLIENTE = 1;
	private static final Integer CADASTRAR_PEDIDO = 2;
	private static final Integer CADASTRAR_PIZZA = 3;
	
	BufferedReader reader;
 
	public UIConcreto(ControlePizzaria controlePizzaria){
		this.controlePizzaria = controlePizzaria;
		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	@Override
	public void inicializarUI(){
		menu();
	}
	
		
	private void menu(){
		
		while(true){
			
			System.out.println("-----------------------");
			System.out.println("PIZZARIA DO MANOLO");
			System.out.println("-----------------------");
			System.out.println("[" + CADASTRAR_CLIENTE + "] CADASTRAR NOVO CLIENTE");
			System.out.println("[" + CADASTRAR_PEDIDO + "] CADASTRAR NOVO PEDIDO");
			System.out.println("[" + CADASTRAR_PIZZA + "] CADASTRAR NOVA PIZZA");

			selecionarOpcao();
		}
	}
	
	private void selecionarOpcao(){
		String opcao = "";
		Integer opcaoInt = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		try {
			opcao = reader.readLine();
			opcaoInt = Integer.valueOf(opcao);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(opcaoInt.equals(CADASTRAR_CLIENTE))
		{
			controlePizzaria.acaoCadastrarCliente();
		}
			
		if(opcaoInt.equals(CADASTRAR_PEDIDO))
		{
			controlePizzaria.acaoCadastrarPedido();
		}
		
		if(opcaoInt.equals(CADASTRAR_PIZZA))
		{
			controlePizzaria.acaoCadastrarPizza();
		}
	}
	
	@Override
	public ClienteVO capturarDadosDoCliente(){
		
		String telefone = null;
		String nome = null;
		String endereco = null;
		
		try {
			System.out.println("TELEFONE DO NOVO CLIENTE: ");
			telefone = reader.readLine();
			
			System.out.println("NOME DO NOVO CLIENTE: ");
			nome = reader.readLine();
			
			System.out.println("ENDERECO DO NOVO CLIENTE: ");
			endereco = reader.readLine();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ClienteVO(null, telefone, nome, endereco);
	}
	
	@Override
	public PizzaVO capturarDadosDaPizza(){
		
		String nomePizza = null;
		String ingredientes = null;
		Float preco = null;
		
		try {
			System.out.println("NOME DA PIZZA: ");
			nomePizza = reader.readLine();
			
			System.out.println("INGREDIENTES DA PIZZA: ");
			ingredientes = reader.readLine();
			
			System.out.println("PRECO DA NOVA PIZZA: ");
			preco = Float.valueOf(reader.readLine());
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new PizzaVO(null, nomePizza, ingredientes, preco);
	}
	
	
	/*@Override
	public PedidoVO capturarDadosDoPedido() {
		String telefoneDoCliente = null;
		String nomeDaPizza = null;
		Integer quantidade = null;
		
		telefoneDoCliente = capturarTelefoneDoCliente();
		controlePizzaria.acaoExibirDadosDoCliente(telefoneDoCliente);
		
		nomeDaPizza = capturarNomeDaPizza();
		controlePizzaria.acaoExibirDadosDaPizza(nomeDaPizza);
		
		quantidade = capturarQuantidadeDesejada();
		
		return new PedidoVO(telefoneDoCliente, nomeDaPizza, quantidade);
	}*/

	@Override
	public String capturarTelefoneDoCliente(){
		String telefoneDoCliente = null;
		
		System.out.println("TELEFONE DO CLIENTE: ");
		
		try {
			telefoneDoCliente = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return telefoneDoCliente;
	}
	

	@Override
	public String capturarNomeDaPizza(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String nomeDaPizza = null;
		
		System.out.println("NOME DA PIZZA: ");
		try {
			nomeDaPizza = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return nomeDaPizza;
	}
	
	@Override
	public Integer capturarQuantidadeDesejada(){
		Integer quantidade = null;		
		System.out.println("QUANTIDADE DESAJADA: ");
		
		try {
			quantidade = Integer.valueOf(reader.readLine());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

		return quantidade;
	}
	
	@Override
	public void exibirInformcacoesDaPizza(PizzaVO pizza){
		System.out.println("INGREDIENTES: " + pizza.obterIngredientes());
		System.out.println("PRECO: " + pizza.obterPreco() + "\n");
	}
	
	
	@Override
	public void exibirInformacoesDoCliente(ClienteVO cliente){
		System.out.println("NOME: " + cliente.obterNome());
		System.out.println("ENDERECO: " + cliente.obterEndereco() + "\n");
	}
	
	
	@Override
	public Boolean solicitarConfirmacao(){
		System.out.println("CONFIRMA? [S/N]");
		String confirmacao = "";
		
		try {
			confirmacao = reader.readLine();
			
			if(confirmacao.equals("S") || confirmacao.equals("s"))
				return true;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	public void exibirMensagem(String mensagem){
		System.out.println(mensagem);
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
