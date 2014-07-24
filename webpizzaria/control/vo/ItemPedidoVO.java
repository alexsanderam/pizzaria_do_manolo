package vo;

public class ItemPedidoVO {

	private PizzaVO pizza;
	private Integer quantidade;
	private Double subtotal;
	
	public ItemPedidoVO(){
		
	}
	
	public ItemPedidoVO(PizzaVO pizza, Integer quantidade){
		this.pizza = pizza;
		this.quantidade = quantidade;
	}

	public PizzaVO getPizza() {
		return pizza;
	}

	public void setPizza(PizzaVO pizza) {
		this.pizza = pizza;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	
	
		
	
}
