package br.com.darkbook.command;

import br.com.darkbook.entidade.EntidadeDominio;
import br.com.darkbook.util.Resultado;

public interface ICommand {
	Resultado executar(EntidadeDominio entidade);
}
