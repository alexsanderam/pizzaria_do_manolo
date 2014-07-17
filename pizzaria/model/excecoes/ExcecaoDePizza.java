package excecoes;

public class ExcecaoDePizza extends Exception{


	private static final long serialVersionUID = -5077720500917035690L;

	public ExcecaoDePizza(String key, Throwable throwable){
		super(key, throwable);
	}
	
	public ExcecaoDePizza(String key){
		super(key);
	}
	
}
