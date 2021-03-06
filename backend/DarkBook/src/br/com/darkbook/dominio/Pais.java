package br.com.darkbook.dominio;

import java.util.List;

import br.com.darkbook.entidade.EntidadeDominio;

public class Pais extends EntidadeDominio {
	private String pais;
	private List<Estado> estados;

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
}
