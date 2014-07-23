package vo;

import java.sql.Timestamp;
import java.util.Collection;

public class PedidoVO {

	private ClienteVO clienteVO;
	private Collection<ItemPedidoVO> itensDoPedido;
	private Timestamp dataHora;
	
	
	public PedidoVO(){
		
	}
	
	public PedidoVO(ClienteVO clienteVO, Collection<ItemPedidoVO> itensDoPedido, Timestamp dataHora) {
		this.clienteVO = clienteVO;
		this.dataHora = dataHora;
		this.itensDoPedido = itensDoPedido;
	}
	
	public ClienteVO obterClienteVO() {
		return clienteVO;
	}

	public void definirClienteVO(ClienteVO clienteVO) {
		this.clienteVO = clienteVO;
	}

	public Collection<ItemPedidoVO> obterItensDoPedido() {
		return itensDoPedido;
	}

	public void definirItensDoPedido(Collection<ItemPedidoVO> itensDoPedido) {
		this.itensDoPedido = itensDoPedido;
	}

	public Timestamp obterDataHora() {
		return dataHora;
	}
	
	public void definirDataHora(Timestamp dataHora) {
		this.dataHora = dataHora;
	}

}
