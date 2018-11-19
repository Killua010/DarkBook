package br.com.darkbook.command;

import br.com.darkbook.entidade.Entidade;
import br.com.darkbook.util.Resultado;

public class ConsultarCommand extends AbstractCommand {

	@Override
	public Resultado executar(Entidade entidade) {
		return fachada.consultar(entidade);
	}

}
