package vo;

public class ClienteVO {

	private Long id;
	private String telefone;
	private String nome;
	private String email;
	private String endereco;
	
	
	public ClienteVO(Long id, String email, String nome, String telefone, String endereco) {
		this.id = id;
		this.telefone = telefone;
		this.nome = nome;
		this.endereco = endereco;
		this.email = email;
	}
	

	public Long obterId() {
		return id;
	}
	
	public void definirId(Long id) {
		this.id = id;
	}
	
	public String obterTelefone() {
		return telefone;
	}
	
	public void definirTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String obterNome() {
		return nome;
	}
	
	public void definirNome(String nome) {
		this.nome = nome;
	}
	
	public String obterEndereco() {
		return endereco;
	}
	
	public void definirEndereco(String endereco) {
		this.endereco = endereco;
	}


	public String obterEmail() {
		return email;
	}


	public void definirEmail(String email) {
		this.email = email;
	}
	
}
