package br.com.darkbook.usuario;

import br.com.darkbook.contato.Contato;

public class Usuario extends Pessoa{
	private String senha;
	private Contato contato;

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	

}
