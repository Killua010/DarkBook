package br.com.darkbook.dominio;

import java.util.List;

import br.com.darkbook.entidade.EntidadeDominio;

public class Cliente extends EntidadeDominio{
	private String cpf;
	private Usuario usuario;
	private List<CartaoCredito> cartoes;
	private List<EnderecoEntrega> enderecoEntregas;
	private List<Endereco> enderecoCobrancas;
	
	/*
	 * Métodos GETs e SETs
	 */
	public List<EnderecoEntrega> getEnderecoEntregas() {
		return enderecoEntregas;
	}

	public void setEnderecoEntregas(List<EnderecoEntrega> enderecoEntregas) {
		this.enderecoEntregas = enderecoEntregas;
	}

	public List<Endereco> getEnderecoCobrancas() {
		return enderecoCobrancas;
	}

	public void setEnderecoCobrancas(List<Endereco> enderecoCobrancas) {
		this.enderecoCobrancas = enderecoCobrancas;
	}

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
