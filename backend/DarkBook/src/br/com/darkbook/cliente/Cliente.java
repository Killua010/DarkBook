package br.com.darkbook.cliente;

import java.util.List;

import br.com.darkbook.usuario.Usuario;

public class Cliente{
	private List<CartaoCredito> cartoes;
	private String cpf;
	private Usuario usuario;
	
	public List<CartaoCredito> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<CartaoCredito> cartoes) {
		this.cartoes = cartoes;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
