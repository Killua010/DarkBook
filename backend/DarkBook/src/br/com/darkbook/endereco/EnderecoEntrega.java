package br.com.darkbook.endereco;

public class EnderecoEntrega extends Endereco{
	private String nomeComposto;
	private boolean favorito;
	
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
}
