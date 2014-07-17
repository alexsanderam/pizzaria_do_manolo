package vo;

import java.sql.Timestamp;

public class PedidoVO {

	private String telefoneDoCliente;
	private String nomeDaPizza;
	private Integer quantidade;
	private Timestamp dataHora;
	
	
	
	public PedidoVO(String telefoneDoCliente, String nomeDaPizza, Integer quantidade) {
		super();
		this.telefoneDoCliente = telefoneDoCliente;
		this.nomeDaPizza = nomeDaPizza;
		this.quantidade = quantidade;
		this.dataHora = null;
	}
	

	public String obterTelefoneDoCliente() {
		return telefoneDoCliente;
	}
	
	public void definirTelefoneDoCliente(String telefoneDoCliente) {
		this.telefoneDoCliente = telefoneDoCliente;
	}
	
	public String obterNomeDaPizza() {
		return nomeDaPizza;
	}
	
	public void definirNomeDaPizza(String nomeDaPizza) {
		this.nomeDaPizza = nomeDaPizza;
	}
	
	public Integer obterQuantidade() {
		return quantidade;
	}
	
	public void definirQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Timestamp obterDataHora() {
		return dataHora;
	}
	
	public void definirDataHora(Timestamp dataHora) {
		this.dataHora = dataHora;
	}
	
	
	
}
