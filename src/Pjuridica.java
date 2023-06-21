public class Pjuridica extends Cliente {
	
	private String cnpj;

	public Pjuridica(String nome, String senha, String cnpj ) {
		super(nome, senha);
		this.cnpj = cnpj;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setNome(String nome) {
		this.setNome(nome);	
	}
	
}
