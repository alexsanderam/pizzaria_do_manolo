package excecoes;

public class ExcecaoDeCliente extends Exception{

	private static final long serialVersionUID = 423375199396727847L;

	public ExcecaoDeCliente(String key, Throwable causa){
		super(key, causa);
	}
	
	public ExcecaoDeCliente(String key){
		super(key);
	}
	
}
