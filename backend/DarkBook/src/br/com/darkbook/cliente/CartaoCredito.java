package br.com.darkbook.cliente;

public class CartaoCredito {
	private Bandeira bandeira;
	private String numero;
	private String nomeImpresso;
	private String codSeguranca;
	private boolean preferencial;
	
	public String validarCartao() {
		String erro = "";
		
		if(null == bandeira) {
			erro += "O dado da bandeira do cartão é obrigatorio\n";
		}
		
		if(null == codSeguranca || codSeguranca.isEmpty()) {
			erro += "O dado do código de segurança do cartão é obrigatorio\n";
		}
		
		if(null == nomeImpresso || nomeImpresso.isEmpty()) {
			erro += "O dado do nome impresso do cartão é obrigatorio\n";
		}
		
		if(null == numero || numero.isEmpty()) {
			erro += "O dado do numero do cartão é obrigatorio\n";
		}
		
		return erro;
	}
	
	/*
	 * Métodos GETs e SETs
	 */
	public Bandeira getBandeira() {
		return bandeira;
	}
	public void setBandeira(Bandeira bandeira) {
		this.bandeira = bandeira;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNomeImpresso() {
		return nomeImpresso;
	}
	public void setNomeImpresso(String nomeImpresso) {
		this.nomeImpresso = nomeImpresso;
	}
	public String getCodSeguranca() {
		return codSeguranca;
	}
	public void setCodSeguranca(String codSeguranca) {
		this.codSeguranca = codSeguranca;
	}
	public boolean isPreferencial() {
		return preferencial;
	}
	public void setPreferencial(boolean preferencial) {
		this.preferencial = preferencial;
	}
}
