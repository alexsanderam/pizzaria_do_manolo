package dominio;

import java.sql.Timestamp;

import excecoes.ExcecaoDePedido;

public class Pedido {

	private Cliente cliente;
	private Pizza pizza;
	private Integer quantidade;
	private Timestamp dataHora;
	
	private Pedido(Cliente cliente, Pizza pizza, Integer quantidade){
		this.cliente = cliente;
		this.pizza = pizza;
		this.quantidade = quantidade;
		this.dataHora = null;
	}
	
	public static Pedido novoPedido(Cliente cliente, Pizza pizza, Integer quantidade) throws ExcecaoDePedido{
		validarDados(cliente, pizza, quantidade);
		
		return new Pedido(cliente, pizza, quantidade);
	}
	
	private static void validarDados(Cliente cliente, Pizza pizza, Integer quantidade) throws ExcecaoDePedido{
		validarCliente(cliente);
		validarPizza(pizza);
		validarQuantidade(quantidade);
	}
	
	private static void validarCliente(Cliente cliente) throws ExcecaoDePedido{
		if(cliente == null)
			throw new ExcecaoDePedido("pedido.cliente.invalido");
	}

	private static void validarPizza(Pizza pizza) throws ExcecaoDePedido{
		if(pizza == null)
			throw new ExcecaoDePedido("pedido.pizza.invalida");
	}
	
	private static void validarDataHora(Timestamp dataHora) throws ExcecaoDePedido{
		if(dataHora == null)
			throw new ExcecaoDePedido("pedido.data_hora.invalida");
	}
	
	private static void validarQuantidade(Integer quantidade) throws ExcecaoDePedido{
		if((quantidade == null) || (quantidade <= 0))
			throw new ExcecaoDePedido("pedido.quantidade.invalida");		
	}
	

	public Cliente obterCliente() {
		return cliente;
	}

	public void definirCliente(Cliente cliente) throws ExcecaoDePedido {
		validarCliente(cliente);
		this.cliente = cliente;
	}

	public Pizza obterPizza() {
		return this.pizza;
	}

	public void definirPizza(Pizza pizza) throws ExcecaoDePedido {
		validarPizza(pizza);
		this.pizza = pizza;
	}
	
	public Timestamp obterDataHora() {
		return this.dataHora;
	}
	
	public void definirDataHora(Timestamp dataHora) throws ExcecaoDePedido{
		validarDataHora(dataHora);
		this.dataHora = dataHora;
	}

	public Integer obterQuantidade() {
		return this.quantidade;
	}

	public void definirQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
