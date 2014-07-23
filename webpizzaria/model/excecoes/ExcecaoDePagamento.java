package excecoes;

public class ExcecaoDePagamento extends Exception {

	private static final long serialVersionUID = 6706560683733928682L;
	
	public ExcecaoDePagamento(String key){
		super(key);
	}
	
	public ExcecaoDePagamento(String key, Throwable causa){
		super(key, causa);
	}	

}
