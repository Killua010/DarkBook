package br.com.darkbook.command;

import br.com.darkbook.entidade.EntidadeDominio;
import br.com.darkbook.util.Resultado;

public class SalvarCommand extends AbstractCommand {

	@Override
	public Resultado executar(EntidadeDominio entidade) {
		return fachada.salvar(entidade);
	}

}
