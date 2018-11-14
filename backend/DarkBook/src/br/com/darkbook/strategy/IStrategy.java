package br.com.darkbook.strategy;

import br.com.darkbook.entidade.Entidade;
import br.com.darkbook.util.Resultado;
//TODO
public interface IStrategy {
	public Resultado processar(Entidade entidade);
}
