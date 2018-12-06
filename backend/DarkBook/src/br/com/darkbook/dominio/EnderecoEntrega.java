package br.com.darkbook.dominio;

public class EnderecoEntrega extends Endereco{
	private String nomeComposto;
	
	/*
	 * Métodos GETs e SETs
	 */
	public String getNomeComposto() {
		return nomeComposto;
	}
	public void setNomeComposto(String nomeComposto) {
		this.nomeComposto = nomeComposto;
	}
	
}
