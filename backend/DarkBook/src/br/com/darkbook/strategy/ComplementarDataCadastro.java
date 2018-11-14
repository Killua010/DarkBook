package br.com.darkbook.strategy;

import java.time.LocalDateTime;

import br.com.darkbook.entidade.Entidade;
import br.com.darkbook.util.Resultado;

public class ComplementarDataCadastro implements IStrategy {

	@Override
	public Resultado processar(Entidade entidade) {
		entidade.setStatus(true);
		entidade.setDataHoraRegistro(LocalDateTime.now());
		return null;
	}

}
