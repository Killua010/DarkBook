package br.com.darkbook.util;

import br.com.darkbook.entidade.Entidade;

public class Resultado {
	private String mensagem;
	private Entidade entidade;
	public Resultado(String mensagem, Entidade entidade) {
		super();
		this.mensagem = mensagem;
		this.entidade = entidade;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Entidade getEntidade() {
		return entidade;
	}
	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}
	
}
