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
	

	public ClienteVO() {
		
	}


	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
}
