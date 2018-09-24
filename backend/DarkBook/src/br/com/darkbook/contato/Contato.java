package br.com.darkbook.contato;

import java.util.List;

public class Contato {
	private List<Telefone> telefones;
	private String email;
	
	public List<Telefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
