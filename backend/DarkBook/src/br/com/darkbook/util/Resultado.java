package br.com.darkbook.util;

import java.util.ArrayList;
import java.util.List;

import br.com.darkbook.entidade.Entidade;

public class Resultado {
	private StringBuilder mensagens = new StringBuilder();
	private List<Entidade> entidades;

	public List<Entidade> getEntidades() {
		return entidades;
	}
	public void setEntidades(List<Entidade> entidades) {
		this.entidades = entidades;
	}
	public StringBuilder getMensagens() {
		return mensagens;
	}
	public void setMensagens(StringBuilder mensagens) {
		this.mensagens = mensagens;
	}
	public void addEntidade(Entidade entidade) {
		if(null == entidades) {
			entidades = new ArrayList<>();
		}
		entidades.add(entidade);
	}
	
}
