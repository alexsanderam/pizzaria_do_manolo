package dominio;

import excecoes.ExcecaoDePizza;


public class Pizza {
	private Long id;
	private String nomePizza;
	private String ingredientes;
	private Float preco;
	
	
	private Pizza(String nomePizza, String ingredientes, Float preco){
		this.id = null;
		this.nomePizza = nomePizza;
		this.ingredientes = ingredientes;
		this.preco = preco;
	}
	
	
	public static Pizza criarPizza(String nomePizza, String ingredientes, Float preco) throws ExcecaoDePizza{
		validarDados(nomePizza, ingredientes, preco);
		return new Pizza(nomePizza, ingredientes, preco);
	}
	
	private static void validarDados(String nomePizza, String ingredientes, Float preco) throws ExcecaoDePizza{
		validarNomePizza(nomePizza);
		validarIngredientes(ingredientes);
		validarPreco(preco);
	}
	
	
	private static void validarNomePizza(String nomePizza) throws ExcecaoDePizza{
		if((nomePizza == null) || (nomePizza.isEmpty()))
			throw new ExcecaoDePizza("pizza.nome.vazio"); 
	}
	
	
	private static  void validarIngredientes(String ingredientes) throws ExcecaoDePizza{
		if((ingredientes == null) || (ingredientes.isEmpty()))
			throw new ExcecaoDePizza("pizza.ingredientes.vazio"); 
	}

	private static void validarPreco(Float preco) throws ExcecaoDePizza{
		if((preco == null) || (preco < 0))
			throw new ExcecaoDePizza("pizza.preco.invalido"); 
	}
	

	private static void validarId(Long id) throws ExcecaoDePizza{
		if((id == null) || (id <= 0))
			throw new ExcecaoDePizza("pizza.identificador.invalido");				
	}
		
	public void definirNomePizza(String nomePizza) throws ExcecaoDePizza {
		validarNomePizza(nomePizza);
		this.nomePizza = nomePizza;
	}


	public void definirIngredientes(String ingredientes) throws ExcecaoDePizza {
		validarIngredientes(ingredientes);
		this.ingredientes = ingredientes;
	}


	public void definirPreco(Float preco) throws ExcecaoDePizza {
		validarPreco(preco);
		this.preco = preco;
	}
	
	public void definirId(Long id) throws ExcecaoDePizza {
		validarId(id);
		this.id = id;
	}

	public Long obterId(){
		return this.id;
	}

	public String obterNomePizza(){
		return this.nomePizza;
	}
	
	public String obterIngredientes(){
		return this.ingredientes;
	}
	
	public Float obterPreco(){
		return this.preco;
	}
}
