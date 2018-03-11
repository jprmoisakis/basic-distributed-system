package server;

public class User {
	
	private String cpf;
	private String nome;
	private String idade;
	
	public User( String cpf, String nome, String idade){
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
	}
		
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

}
