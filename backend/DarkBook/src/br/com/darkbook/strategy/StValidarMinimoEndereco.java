package br.com.darkbook.strategy;

import br.com.darkbook.dominio.Cliente;
import br.com.darkbook.dominio.Endereco;
import br.com.darkbook.entidade.EntidadeDominio;

public class StValidarMinimoEndereco extends AbstractStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		mensagem.setLength(0);
		int contador = 0;
		Cliente cliente = (Cliente) entidade;
		
		for(Endereco e : cliente.getEnderecoCobrancas()) {
			if(false == e.isStatus()) {
				contador++;
			}
		}
		
		if(contador != 0 && contador == cliente.getEnderecoCobrancas().size()) {
			mensagem.append("É obrigatório o cadastro de no minimo um endereço de cobrança");
		}
		
		contador = 0;
		for(Endereco e : cliente.getEnderecoEntregas()) {
			if(false == e.isStatus()) {
				contador++;
			}
		}
		
		if(contador != 0 && contador == cliente.getEnderecoEntregas().size()) {
			mensagem.append("É obrigatório o cadastro de no minimo um endereço de entrega");
		}
		
		return mensagem.toString();
	}

}
