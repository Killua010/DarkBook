package br.com.darkbook.strategy;

import br.com.darkbook.entidade.Entidade;

public interface IStrategy {
	public String processar(Entidade entidade);
}
