package pizzariaDAO;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;

import dominio.Cliente;
import dominio.ItemPedido;
import dominio.Pagamento;
import dominio.Pedido;
import excecoes.ExcecaoDAO;
import excecoes.ExcecaoDeItemPedido;
import excecoes.ExcecaoDePedido;
import excecoes.ExcecaoDePizza;
 
public class PedidoDAOConcreto implements PedidoDAO{
 
    private Connection conexao = null;
     
    public PedidoDAOConcreto() throws ExcecaoDAO{
        this.conexao = criarConexao();
    }
     
     
    protected Connection criarConexao() throws ExcecaoDAO{
        return ConnectionFactoryConcreto.obterInstancia().obterConexao();
    }
     
    @Override
    public void incluir(Pedido pedido) throws ExcecaoDAO {
         
        if(conexao == null)
            throw new ExcecaoDAO("pedido_dao.conexao_nao_estabelecida");
         
        String sql = "INSERT INTO webpizzaria.Pedido(id_cliente_fk, id_pagamento_fk, data_hora) values (?, ?, current_timestamp)";
        PreparedStatement stmt;
        ResultSet rs;
         
        try {
            stmt = conexao.prepareStatement(sql);
            
            Long id_cliente = pedido.obterCliente().obterId();
            stmt.setLong(1, id_cliente);
            
            Long id_pagamento = pedido.obterPagamento().obterId();
            stmt.setLong(1, id_pagamento);
             
            rs = stmt.executeQuery();
            
            if (rs.next()){
            	pedido.definirId(rs.getLong("id"));
            }
            
            incluirItensDoPedido(pedido);
             
        } catch (Exception e) {
            throw new ExcecaoDAO("pedido_dao.nao_foi_possivel_incluir_o_pedido", e);
        }
    }
    
    
    private void incluirItensDoPedido(Pedido pedido) throws ExcecaoDAO{
    	
    	/*Conexao ja foi estabelecida pelo incluir*/
    	
    	Collection<ItemPedido> itensDoPedido = pedido.obterItens();
    	try {
    		
			ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAOConcreto();
			
	        for (ItemPedido itemPedido : itensDoPedido) {
	        	itemPedidoDAO.incluir(itemPedido);
			}
	        
	        itemPedidoDAO.encerrarConexao();
	        
		} catch (ExcecaoDAO e) {
			throw new ExcecaoDAO("pedido_dao.nao_foi_possivel_incluir_os_intes_do_pedido", e);
		}      
    }
    
    
	@Override
	public Pedido buscar(Long id) throws ExcecaoDAO, ExcecaoDePedido {
        if(conexao == null)
            throw new ExcecaoDAO("pedido_dao.conexao_nao_estabelecida");
         
        Pedido pedido = null;
 
        String sql = "SELECT id_cliente_fk, id_pagamento_fk, data_hora  FROM webpizzaria.Pedido WHERE id = ?";
        PreparedStatement stmt;
        ResultSet rs;
         
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);
             
            rs = stmt.executeQuery();
            
            ClienteDAO clienteDAO = new ClienteDAOConcreto();
             
            if (rs.next()){
            	Long id_cliente = rs.getLong("id_cliente_fk");
            	Cliente cliente = null;
				cliente = recuperarClientePeloIdentificador(id_cliente);
            	
            	Long id_pagamento = rs.getLong("id_pagamento_fk");
            	Pagamento pagamento = null;
				pagamento = recuperarPagamentoPeloIdentificador(id_pagamento);
				
				Timestamp dataHora = rs.getTimestamp("data_hora");
            	
            	pedido = Pedido.novoPedido(cliente);
            	pedido.definirDataHora(dataHora);
            	pedido.definirPagamento(pagamento);
            	pedido.definirId(id);
            	
            	Collection<ItemPedido> itensDoPedido = recuperarItensDoPedido(pedido);
    			pedido.definirItens(itensDoPedido);
				
				clienteDAO.encerrarConexao();
            }
             
        } catch (SQLException e) {
 
            throw new ExcecaoDAO("pedido_dao.nao_foi_possivel_localizar_o_pedido", e);
        }
        
        if(pedido == null)
        	throw new ExcecaoDAO("pedido_dao.nao_foi_possivel_localizar_o_pedido");
         
        return pedido;
	}
	
	
	private Collection<ItemPedido> recuperarItensDoPedido(Pedido pedido) throws ExcecaoDAO{
    	Collection<ItemPedido> itensDoPedido;
    	
		try {
	        ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAOConcreto();
			itensDoPedido = itemPedidoDAO.buscarTodosDoPedido(pedido);
			
			itemPedidoDAO.encerrarConexao();
		} catch (ExcecaoDeItemPedido | ExcecaoDePizza | ExcecaoDAO e) {
			throw new ExcecaoDAO("pedido_dao.nao_foi_possivel_localizar_o_pedido", e);
		}
		
		return itensDoPedido;
	}
	
	
	private Pagamento recuperarPagamentoPeloIdentificador(Long id_pagamento) throws ExcecaoDAO{
		
		Pagamento pagamento = null;
        
		PagamentoDAO pagamentoDAO = new PagamentoDAOConcreto();
		
		try {
			pagamento = pagamentoDAO.buscar(id_pagamento);
			
			pagamentoDAO.encerrarConexao();
		} catch (Exception e) {
			throw new ExcecaoDAO("pedido_dao.nao_foi_possivel_localizar_o_pagamento", e);
		}
        
        return pagamento;
	}
	
	private Cliente recuperarClientePeloIdentificador(Long id_cliente) throws ExcecaoDAO{
		
		Cliente cliente = null;
        
		ClienteDAO clienteDAO = new ClienteDAOConcreto();
		
		try {
			cliente = clienteDAO.buscar(id_cliente);
			
			clienteDAO.encerrarConexao();
		} catch (Exception e) {
			throw new ExcecaoDAO("pedido_dao.nao_foi_possivel_localizar_o_cliente", e);
		}
        
        return cliente;
	}
	

	@Override
	public Collection<Pedido> buscarTodosPedidosDoCliente(Cliente cliente) throws ExcecaoDAO, ExcecaoDePedido {
        if(conexao == null)
            throw new ExcecaoDAO("pedido_dao.conexao_nao_estabelecida");
        
		Collection<Pedido> pedidos = null;
		
		Long id_cliente = cliente.obterId();
		
        String sql = "SELECT id, id_pagamento_fk, data_hora FROM webpizzaria.Pedido WHERE id_cliente_fk = ? ORDERBY data_hora DESC";
        PreparedStatement stmt;
        ResultSet rs;
        
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id_cliente);
            
            rs = stmt.executeQuery();
            
        	Pedido pedido = null;
            
            while(rs.next()){
            	
            	Long id_pedido = rs.getLong("id");
            	
            	Long id_pagamento = rs.getLong("id_pagamento_fk");
            	Pagamento pagamento = null;
				pagamento = recuperarPagamentoPeloIdentificador(id_pagamento);
				
				Timestamp dataHora = rs.getTimestamp("data_hora");
            	
            	pedido = Pedido.novoPedido(cliente);
            	pedido.definirDataHora(dataHora);
            	pedido.definirPagamento(pagamento);
            	pedido.definirId(id_pedido);
            	
            	Collection<ItemPedido> itensDoPedido = recuperarItensDoPedido(pedido);
    			pedido.definirItens(itensDoPedido);
            	
            }
        }             
        catch (SQLException e) { 
        	throw new ExcecaoDAO("pedido_dao.nao_foi_possivel_localizar_os_pedidos_do_cliente", e);
        }
        		
		return pedidos;
	}
     
    @Override
    public void encerrarConexao() throws ExcecaoDAO{
        try {
            this.conexao.close();
        } catch (SQLException e) {
            throw new ExcecaoDAO("pedido_dao.nao_foi_possivel_encerrar_a_conexao");
        }
    }
     
}