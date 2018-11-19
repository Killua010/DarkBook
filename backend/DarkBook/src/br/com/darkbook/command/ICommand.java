package br.com.darkbook.command;

import br.com.darkbook.entidade.Entidade;
import br.com.darkbook.util.Resultado;

public interface ICommand {
	Resultado executar(Entidade entidade);
}
