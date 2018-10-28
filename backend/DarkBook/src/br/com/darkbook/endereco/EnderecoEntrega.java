package br.com.darkbook.endereco;

public class EnderecoEntrega extends Endereco{
	private String nomeComposto;
	private Boolean favorito;
	
	public String validarEndereco() {
		String erro = super.validarEndereco();
		System.out.println(this.getCidade().getCidade());
		if(null == nomeComposto || nomeComposto.isEmpty()) {
			erro += "O dado do nome composto do endereço de entrega é obrigatorio\n";
		}
		
		if(null == favorito) {
			erro += "O dado de favoritoo do endereço de entrega é obrigatorio\n";
		}
		
		return erro;
	}
	
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
}
