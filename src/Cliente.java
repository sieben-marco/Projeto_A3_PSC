// Classe para a entidade Operador
public class Cliente {
	// Atributos com encapsulamento, acesso direto na pr�pria classe.
	private int id;
	private String nome;
	private String senha;
	private boolean autenticado = false;
	
	public Cliente() {
	}
	
	//Contrutor com os "this".
	public Cliente(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
	}

	// Getters e os Setters.
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAutenticado() {
		return autenticado;
	}

	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}
}
