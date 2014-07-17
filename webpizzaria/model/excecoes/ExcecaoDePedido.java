package excecoes;

public class ExcecaoDePedido extends Exception {
	

	private static final long serialVersionUID = -5858896275226910220L;

	public ExcecaoDePedido(String key, Throwable causa){
		super(key, causa);
	}
	
	public ExcecaoDePedido(String key){
		super(key);
	}
}
