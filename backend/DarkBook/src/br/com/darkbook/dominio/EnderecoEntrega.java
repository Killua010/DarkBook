package br.com.darkbook.dominio;

public class EnderecoEntrega extends Endereco{
	private String nomeComposto;
	private Boolean favorito;
	
	/*
	 * Métodos GETs e SETs
	 */
	public String getNomeComposto() {
		return nomeComposto;
	}
	public void setNomeComposto(String nomeComposto) {
		this.nomeComposto = nomeComposto;
	}
	public boolean isFavorito() {
		return favorito;
	}
	public void setFavorito(boolean favorito) {
		this.favorito = favorito;
	}
	public Boolean getFavorito() {
		return favorito;
	}
	public void setFavorito(Boolean favorito) {
		this.favorito = favorito;
	}
	
}
