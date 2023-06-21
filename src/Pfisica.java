public class Pfisica extends Cliente {
	
	private String cpf;

	public Pfisica(String nome, String senha, String cpf) {
		super(nome, senha);
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setNome(String nome) {
		this.setNome(nome);
	}
}


