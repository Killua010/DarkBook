package br.com.darkbook.usuario;

import java.util.List;

import br.com.darkbook.contato.Contato;

public class Usuario extends Pessoa{
	private String senha;
	private List<Contato> contatos;

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	

}
