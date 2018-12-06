package br.com.darkbook.dominio;

import br.com.darkbook.entidade.EntidadeDominio;

public class CartaoCredito extends EntidadeDominio{
	private Bandeira bandeira;
	private String numero;
	private String nomeImpresso;
	private String codSeguranca;
	private boolean preferencial;

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
