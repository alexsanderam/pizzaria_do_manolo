package vo;

public class PizzaVO {

	private Long id;
	private String nomePizza;
	private String ingredientes;
	private Float preco;
	
	
	public PizzaVO(Long id, String nomePizza, String ingredientes, Float preco) {
		this.id = id;
		this.nomePizza = nomePizza;
		this.ingredientes = ingredientes;
		this.preco = preco;
	}
	

	public Long obterId() {
		return id;
	}
	
	public void definirId(Long id) {
		this.id = id;
	}
	
	public String obterNomePizza() {
		return nomePizza;
	}
	
	public void definirNomePizza(String nomePizza) {
		this.nomePizza = nomePizza;
	}
	
	public String obterIngredientes() {
		return ingredientes;
	}
	
	public void definirIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}
	
	public Float obterPreco() {
		return preco;
	}
	
	public void definirPreco(Float preco) {
		this.preco = preco;
	}
	
	
	
}
