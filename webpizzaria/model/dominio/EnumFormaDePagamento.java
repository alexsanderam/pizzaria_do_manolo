package dominio;

public enum EnumFormaDePagamento {
	DINHEIRO_SEM_TROCO("Dinheiro sem troco"),DINHEIRO_COM_TROCO("Dinheiro com troco"),CARTAO_DE_CREDITO("Cartão de crédito"), CARTAO_DE_DEBITO("Cartão de débito");
	
	private final String valor;
	
	EnumFormaDePagamento (String valorOpcao){
		valor = valorOpcao;
	}
	
	public String obterValor(){
		return valor;
	}
}
