package br.com.darkbook.strategy;

import br.com.darkbook.dominio.CartaoCredito;
import br.com.darkbook.entidade.EntidadeDominio;

public class StValidarCartao extends AbstractStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		CartaoCredito cartao = (CartaoCredito) entidade;
		
		if(null == cartao.getBandeira()) {
			mensagem.append("O dado da bandeira do cartão é obrigatorio");
		}
		
		if(null == cartao.getCodSeguranca() || cartao.getCodSeguranca().isEmpty()) {
			mensagem.append("O dado do código de segurança do cartão é obrigatorio");
		}
		
		if(null == cartao.getNomeImpresso() || cartao.getNomeImpresso().isEmpty()) {
			mensagem.append("O dado do nome impresso do cartão é obrigatorio");
		}
		
		if(null == cartao.getNumero() || cartao.getNumero().isEmpty()) {
			mensagem.append("O dado do numero do cartão é obrigatorio");
		}
		
		return mensagem.toString();
	}

}
