public class Pjuridica extends Cliente {
	
	private String cnpj;
	
	public Pjuridica(String cnpj ) {
		this.cnpj = cnpj;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
}
