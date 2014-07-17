package excecoes;

public class ExcecaoDAO extends Exception {

	private static final long serialVersionUID = -7149175035103363528L;

	public ExcecaoDAO(String key, Throwable causa){
		super(key, causa);
	}
	
	public ExcecaoDAO(String key){
		super(key);
	}
	
}
