package pizzaria;

import vo.ClienteVO;
import vo.PizzaVO;

public interface UserInterface {

	void inicializarUI();

	void exibirMensagem(String mensagem);

	ClienteVO capturarDadosDoCliente();

	PizzaVO capturarDadosDaPizza();

	Integer capturarQuantidadeDesejada();

	void exibirInformcacoesDaPizza(PizzaVO pizza);

	void exibirInformacoesDoCliente(ClienteVO cliente);

	Boolean solicitarConfirmacao();

	//PedidoVO capturarDadosDoPedido();
	
	String capturarTelefoneDoCliente();
	
	String capturarNomeDaPizza();
	
}
