package vo;

public class PizzaVO {

	private Long id;
	private String nomePizza;
	private String ingredientes;
	private Float preco;
	
	public PizzaVO(){
		
	}
	
	public PizzaVO(Long id, String nomePizza, String ingredientes, Float preco) {
		this.id = id;
		this.nomePizza = nomePizza;
		this.ingredientes = ingredientes;
		this.preco = preco;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNomePizza() {
		return nomePizza;
	}
	
	public void setNomePizza(String nomePizza) {
		this.nomePizza = nomePizza;
	}
	
	public String getIngredientes() {
		return ingredientes;
	}
	
	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}
	
	public Float getPreco() {
		return preco;
	}
	
	public void setPreco(Float preco) {
		this.preco = preco;
	}
	
	
	
}
