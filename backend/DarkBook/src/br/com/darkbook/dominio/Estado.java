package br.com.darkbook.dominio;

import java.util.List;

import br.com.darkbook.entidade.EntidadeDominio;

public class Estado extends EntidadeDominio {
	private String estado;
	private String sigla;
	private Pais pais;
	private List<Cidade> cidades;
	
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public List<Cidade> getCidades() {
		return cidades;
	}
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
