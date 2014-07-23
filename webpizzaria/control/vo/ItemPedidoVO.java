package vo;

public class ItemPedidoVO {

	private PizzaVO pizza;
	private Integer quantidade;
	
	public ItemPedidoVO(){
		
	}
	
	public ItemPedidoVO(PizzaVO pizza, Integer quantidade){
		this.pizza = pizza;
		this.quantidade = quantidade;
	}

	public PizzaVO obterPizza() {
		return pizza;
	}

	public void definirPizza(PizzaVO pizza) {
		this.pizza = pizza;
	}

	public Integer obterQuantidade() {
		return quantidade;
	}

	public void definirQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
		
	
}
