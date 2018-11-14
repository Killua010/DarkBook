package br.com.darkbook.dominio;

import br.com.darkbook.entidade.Entidade;

public class Cidade extends Entidade {
	private Estado estado;
	private String cidade;
	
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}
