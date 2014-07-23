package vo;

import dominio.EnumFormaDePagamento;

public class PagamentoVO {

	private Long id;
	private EnumFormaDePagamento formaDePagamento;
	private Float valorRecebido;
	
	public PagamentoVO(){
		
	}
	
	public PagamentoVO(Long id, EnumFormaDePagamento formaDePagamento, Float valorRecebido){
		this.id = id;
		this.formaDePagamento = formaDePagamento;
		this.valorRecebido = valorRecebido;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public EnumFormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}
	
	public void setFormaDePagamento(EnumFormaDePagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}
	
	public Float getValorRecebido() {
		return valorRecebido;
	}
	
	public void setValorRecebido(Float valorRecebido) {
		this.valorRecebido = valorRecebido;
	}
	
}
