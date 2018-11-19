package br.com.darkbook.strategy;

import java.time.LocalDateTime;

import br.com.darkbook.entidade.Entidade;

public class ComplementarDataCadastro implements IStrategy {

	@Override
	public String processar(Entidade entidade) {
		entidade.setStatus(true);
		entidade.setDataHoraRegistro(LocalDateTime.now());
		return null;
	}

}
