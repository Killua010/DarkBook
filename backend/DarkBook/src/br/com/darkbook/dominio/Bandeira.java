package br.com.darkbook.dominio;

import br.com.darkbook.entidade.EntidadeDominio;

public class Bandeira extends EntidadeDominio{
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
