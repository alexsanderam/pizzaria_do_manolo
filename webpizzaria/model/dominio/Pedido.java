package dominio;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import excecoes.ExcecaoDePedido;

public class Pedido {

	private Integer id;
	private Cliente cliente;
	private Timestamp dataHora;
	private Collection<ItemPedido> itens;
	
	private Pedido(Cliente cliente, Float valor){
		this.id = null;
		this.cliente = cliente;
		this.dataHora = null;
		this.itens = new ArrayList<ItemPedido>();
	}
	
	public static Pedido novoPedido(Cliente cliente) throws ExcecaoDePedido{
		validarDados(cliente);
		
		return new Pedido(cliente);
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
