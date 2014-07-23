package dominio;

import excecoes.ExcecaoDeCliente;

public class Cliente {
	private Long id;
	private String telefone;
	private String nome;
	private String endereco;
	
	private Cliente(String telefone, String nome, String endereco){
		this.id = null;
		this.telefone = telefone;
		this.nome = nome;
		this.endereco = endereco;
	}
	
	
	public static Cliente criarCliente(String telefone, String nome, String endereco) throws ExcecaoDeCliente{
		validarDados(telefone, nome, endereco);
		return new Cliente(telefone, nome, endereco);
	}
	
	private static void validarDados(String telefone, String nome, String endereco) throws ExcecaoDeCliente{
		validarNome(nome);
		validarTelefone(telefone);
		validarEndereco(endereco);
	}
	
	private static void validarTelefone(String telefone) throws ExcecaoDeCliente{
		if((telefone == null) || (telefone.isEmpty()))
			throw new ExcecaoDeCliente("cliente.telefone.vazio");		
	}
	
	private static void validarNome(String nome) throws ExcecaoDeCliente{
		if((nome == null) || (nome.isEmpty()))
			throw new ExcecaoDeCliente("cliente.nome.vazio");		
	}
	
	private static void validarEndereco(String endereco) throws ExcecaoDeCliente{
		if((endereco == null) || (endereco.isEmpty()))
			throw new ExcecaoDeCliente("cliente.endereco.vazio");		
	}
	
	private static void validarId(Long id) throws ExcecaoDeCliente{
		if((id == null) || (id <= 0))
			throw new ExcecaoDeCliente("cliente.identificador.invalido");				
	}
	
	public void definirTelefone(String telefone) throws ExcecaoDeCliente{
		validarTelefone(telefone);
		this.telefone = telefone;
	}
	
	public void definirNome(String nome) throws ExcecaoDeCliente{
		validarNome(nome);
		this.nome = nome;
	}
	
	public void definirEndereco(String endereco) throws ExcecaoDeCliente{
		validarEndereco(endereco);
		this.endereco = endereco;
	}
	
	public void definirId(Long id) throws ExcecaoDeCliente{
		validarId(id);
		this.id = id;
	}
	
	public Long obterId(){
		return this.id;
	}
	
	public String obterTelefone(){
		return this.telefone;
	}
	
	public String obterNome(){
		return this.nome;
	}
	
	public String obterEndereco(){
		return this.endereco;
	}
}
