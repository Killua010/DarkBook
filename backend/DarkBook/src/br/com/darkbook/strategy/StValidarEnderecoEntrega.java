package br.com.darkbook.strategy;

import br.com.darkbook.dominio.EnderecoEntrega;
import br.com.darkbook.entidade.EntidadeDominio;

public class StValidarEnderecoEntrega extends AbstractStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		EnderecoEntrega enderecoEntrega = (EnderecoEntrega) entidade;
		IStrategy strategy = new StValidarEndereco();
		
		mensagem.append(strategy.processar(enderecoEntrega));
		
		if(null == enderecoEntrega.getNomeComposto() || enderecoEntrega.getNomeComposto().isEmpty()) {
			mensagem.append("O dado do nome composto do endereço de entrega é obrigatorio");
		}
		
		if(null == enderecoEntrega.getFavorito()) {
			mensagem.append("O dado de favoritoo do endereço de entrega é obrigatorio");
		}
		
		return mensagem.toString();
	}

}
