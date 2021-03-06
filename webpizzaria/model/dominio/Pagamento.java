package dominio;

import excecoes.ExcecaoDePagamento;


public class Pagamento {

	private Long id;
	private EnumFormaDePagamento formaDePagamento;
	private Float valorRecebido;
	
	private Pagamento(EnumFormaDePagamento formaDePagamento, Float valorRecebido){
		this.formaDePagamento = formaDePagamento;
		this.valorRecebido = valorRecebido;
		this.id = null;
	}
	
	public static Pagamento novoPagamento(EnumFormaDePagamento formaDePagamento, Float valorRecebido) throws ExcecaoDePagamento{
		validarDados(formaDePagamento, valorRecebido);
		return new Pagamento(formaDePagamento, valorRecebido);
	}
		
	private static void validarDados(EnumFormaDePagamento formaDePagamento, Float valorRecebido) throws ExcecaoDePagamento{
		validarFormaDePagamento(formaDePagamento);
		validarValorRecebido(valorRecebido);
	}
	
	private static void validarFormaDePagamento(EnumFormaDePagamento formaDePagamento) throws ExcecaoDePagamento{
		if(formaDePagamento == null)
			throw new ExcecaoDePagamento("pagamento.forma_de_pagamento.invalida");
	}
	
	private static void validarValorRecebido(Float valorRecebido) throws ExcecaoDePagamento{
		if(valorRecebido == null || valorRecebido < 0)
			throw new ExcecaoDePagamento("pagamento.valor_recebido.invalido");
	}
	
	private static void validarId(Long id) throws ExcecaoDePagamento{
		if(id == null || id <= 0)
			throw new ExcecaoDePagamento("pagamento.id.invalido");
	}


	public Long obterId() {
		return id;
	}

	public void definirId(Long id) throws ExcecaoDePagamento {
		validarId(id);
		this.id = id;
	}

	public EnumFormaDePagamento obterFormaDePagamento() {
		return formaDePagamento;
	}

	public void definirFormaDePagamento(EnumFormaDePagamento formaDePagamento) throws ExcecaoDePagamento {
		validarFormaDePagamento(formaDePagamento);
		this.formaDePagamento = formaDePagamento;
	}

	public Float obterValorRecebido() {
		return valorRecebido;
	}

	public void definirValorRecebido(Float valorRecebido) throws ExcecaoDePagamento {
		validarValorRecebido(valorRecebido);
		this.valorRecebido = valorRecebido;
	}
	
	public Float calcularTroco(Float valorTotal) throws ExcecaoDePagamento{
		if(formaDePagamento != EnumFormaDePagamento.DINHEIRO_COM_TROCO)
			throw new ExcecaoDePagamento("pedido.calcular_troco.forma_de_pagamento_nao_possibilita_troco");
		
		Float valorDoTroco = valorRecebido - valorTotal;
		
		if(valorDoTroco < 0)
			throw new ExcecaoDePagamento("pedido.calcular_troco.valor_do_troco_invalido");
		
		return valorDoTroco;
	}
}
