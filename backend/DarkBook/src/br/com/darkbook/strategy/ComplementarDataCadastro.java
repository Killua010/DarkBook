package br.com.darkbook.strategy;

import java.time.LocalDateTime;

import br.com.darkbook.entidade.EntidadeDominio;

public class ComplementarDataCadastro implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		entidade.setStatus(true);
		entidade.setDataHoraRegistro(LocalDateTime.now());
		return null;
	}

}
