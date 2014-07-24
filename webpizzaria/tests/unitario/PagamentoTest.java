package unitario;

import static org.junit.Assert.*;

import org.junit.Test;

import dominio.EnumFormaDePagamento;
import dominio.Pagamento;
import excecoes.ExcecaoDePagamento;

public class PagamentoTest {
	
	private EnumFormaDePagamento formaDePagamento = EnumFormaDePagamento.CARTAO_DE_CREDITO;
	private Float valorRecebido = 200f;
	
	@Test
	public void testNovoPagamento(){

		
		try {
			Pagamento pagamento = Pagamento.novoPagamento(formaDePagamento, valorRecebido);
			assertNotNull(pagamento);
			
			/*Validacao dos valores*/
			assertEquals(formaDePagamento, pagamento.obterFormaDePagamento());
			assertEquals(valorRecebido, pagamento.obterValorRecebido());
		} catch (ExcecaoDePagamento e) {
			e.printStackTrace();
		}	
	}
	
	@Test(expected=ExcecaoDePagamento.class)
	public void testNovoPagamentoFormaDePagamentoInvalida() throws ExcecaoDePagamento{
		EnumFormaDePagamento formaDePagamento = null;

		Pagamento.novoPagamento(formaDePagamento, valorRecebido);
	}
	
	@Test(expected=ExcecaoDePagamento.class)
	public void testNovoPagamentoValorRecebidoInvalido() throws ExcecaoDePagamento{
		Float valorRecebido = -200f;

		Pagamento.novoPagamento(formaDePagamento, valorRecebido);
	}
	
	@Test
	public void testDefinirFormaDePagamento(){
		EnumFormaDePagamento formaDePagamento;
		
		try {
			Pagamento pagamento = Pagamento.novoPagamento(this.formaDePagamento, valorRecebido);
			formaDePagamento = EnumFormaDePagamento.CARTAO_DE_DEBITO;
			pagamento.definirFormaDePagamento(formaDePagamento);
			assertEquals(formaDePagamento, pagamento.obterFormaDePagamento());
		} catch (ExcecaoDePagamento e) {
			e.printStackTrace();
		}	
	}
	
	@Test(expected=ExcecaoDePagamento.class)
	public void testDefinirFormaDePagamentoInvalida() throws ExcecaoDePagamento{
		EnumFormaDePagamento formaDePagamento;

		Pagamento pagamento = Pagamento.novoPagamento(this.formaDePagamento, valorRecebido);
		formaDePagamento = null;
		pagamento.definirFormaDePagamento(formaDePagamento);
		assertEquals(formaDePagamento, pagamento.obterFormaDePagamento());	
	}
	
	@Test
	public void testDefinirValorRecebido(){
		Float valorRecebido;
		
		try {
			Pagamento pagamento = Pagamento.novoPagamento(formaDePagamento, this.valorRecebido);
			valorRecebido = 250f;
			pagamento.definirValorRecebido(valorRecebido);
			assertEquals(valorRecebido, pagamento.obterValorRecebido());
		} catch (ExcecaoDePagamento e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected=ExcecaoDePagamento.class)
	public void testDefinirValorRecebidoInvalido() throws ExcecaoDePagamento{
		Float valorRecebido;
		
		Pagamento pagamento = Pagamento.novoPagamento(formaDePagamento, this.valorRecebido);
		valorRecebido = -1f;
		pagamento.definirValorRecebido(valorRecebido);	
	}
	
	@Test
	public void testCalcularTroco(){
		EnumFormaDePagamento formaDePagamento = EnumFormaDePagamento.DINHEIRO_COM_TROCO;
		try {
			Pagamento pagamento = Pagamento.novoPagamento(formaDePagamento, 220f);
			Float troco = pagamento.calcularTroco(200f);
			Float trocoEsperado = 20f;
			assertEquals(trocoEsperado, troco);
		} catch (ExcecaoDePagamento e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected = ExcecaoDePagamento.class)
	public void testCalcularTrocoInvalido() throws ExcecaoDePagamento{
		EnumFormaDePagamento formaDePagamento = EnumFormaDePagamento.DINHEIRO_COM_TROCO;

		Pagamento pagamento = Pagamento.novoPagamento(formaDePagamento, 220f);
		pagamento.calcularTroco(260f);
	}
	
	@Test(expected = ExcecaoDePagamento.class)
	public void testCalcularTrocoFormaDePagamentoInvalida() throws ExcecaoDePagamento{
		EnumFormaDePagamento formaDePagamento = EnumFormaDePagamento.DINHEIRO_SEM_TROCO;

		Pagamento pagamento = Pagamento.novoPagamento(formaDePagamento, 220f);
		pagamento.calcularTroco(200f);
	}
}
