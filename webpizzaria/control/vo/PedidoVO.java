package vo;

import java.sql.Timestamp;
import java.util.Collection;

public class PedidoVO {

	private ClienteVO clienteVO;
	private Collection<ItemPedidoVO> itensDoPedido;
	private Timestamp dataHora;
	private Float total;
	
	
	public PedidoVO(){
		
	}
	
	public PedidoVO(ClienteVO clienteVO, Collection<ItemPedidoVO> itensDoPedido, Timestamp dataHora) {
		this.clienteVO = clienteVO;
		this.dataHora = dataHora;
		this.itensDoPedido = itensDoPedido;
	}
	
	public ClienteVO getClienteVO() {
		return clienteVO;
	}

	public void setClienteVO(ClienteVO clienteVO) {
		this.clienteVO = clienteVO;
	}

	public Collection<ItemPedidoVO> getItensDoPedido() {
		return itensDoPedido;
	}

	public void setItensDoPedido(Collection<ItemPedidoVO> itensDoPedido) {
		this.itensDoPedido = itensDoPedido;
	}

	public Timestamp getDataHora() {
		return dataHora;
	}
	
	public void setDataHora(Timestamp dataHora) {
		this.dataHora = dataHora;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}
	
	

}
