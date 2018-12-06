package br.com.darkbook.util;

import java.util.ArrayList;
import java.util.List;

import br.com.darkbook.entidade.EntidadeDominio;

public class Resultado {
	private StringBuilder mensagens = new StringBuilder();
	private List<EntidadeDominio> entidades;

	public List<EntidadeDominio> getEntidades() {
		return entidades;
	}
	public void setEntidades(List<EntidadeDominio> entidades) {
		this.entidades = entidades;
	}
	public StringBuilder getMensagens() {
		return mensagens;
	}
	public void setMensagens(StringBuilder mensagens) {
		this.mensagens = mensagens;
	}
	public void addEntidade(EntidadeDominio entidade) {
		if(null == entidades) {
			entidades = new ArrayList<>();
		}
		entidades.add(entidade);
	}
	
}
