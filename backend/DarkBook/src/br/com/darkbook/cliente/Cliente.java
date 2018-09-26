package br.com.darkbook.cliente;

import java.util.List;

import br.com.darkbook.endereco.Endereco;
import br.com.darkbook.endereco.EnderecoEntrega;
import br.com.darkbook.entidade.Entidade;
import br.com.darkbook.usuario.Usuario;

public class Cliente extends Entidade{
	private String cpf;
	private Usuario usuario;
	private List<CartaoCredito> cartoes;
	private List<EnderecoEntrega> enderecoEntregas;
	private List<Endereco> enderecos;
	
	public List<EnderecoEntrega> getEnderecoEntregas() {
		return enderecoEntregas;
	}

	public void setEnderecoEntregas(List<EnderecoEntrega> enderecoEntregas) {
		this.enderecoEntregas = enderecoEntregas;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
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
