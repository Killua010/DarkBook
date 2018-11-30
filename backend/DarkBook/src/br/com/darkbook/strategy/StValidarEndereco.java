package br.com.darkbook.strategy;

import br.com.darkbook.dominio.Endereco;
import br.com.darkbook.entidade.EntidadeDominio;

public class StValidarEndereco extends AbstractStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;
		
		if(null == endereco.getBairro() || endereco.getBairro().isEmpty()) {
			mensagem.append("O dado do bairro do endereço de cobrança é obrigatorio");
		}
		
		if(null == endereco.getCep() || endereco.getCep().isEmpty()) {
			mensagem.append("O dado do cep do endereço de cobrança é obrigatorio");
		}
		
		if(endereco.getCep().length() != 8) {
			mensagem.append("O dado do cep do endereço de cobrança teve ter 8 caracteres");
		}
		
		if(null == endereco.getCidade()) {
			mensagem.append("O dado da cidade do endereço de cobrança é obrigatorio");
			mensagem.append("O dado do estado do endereço de cobrança é obrigatorio");
			mensagem.append("O dado do pais do endereço de cobrança é obrigatorio");
		}

		if(endereco.getCidade().getCidade().isEmpty()) {
			mensagem.append("O dado da cidade do endereço de cobrança é obrigatorio");
		}
		
		if(endereco.getCidade().getEstado().getEstado().isEmpty()) {
			mensagem.append("O dado do estado do endereço de cobrança é obrigatorio");
		}
		
		if(endereco.getCidade().getEstado().getPais().getPais().isEmpty()) {
			mensagem.append("O dado do pais do endereço de cobrança é obrigatorio");
		}
		
		if(null == endereco.getLogradouro() || endereco.getLogradouro().isEmpty()) {
			mensagem.append("O dado do logradouro do endereço de cobrança é obrigatorio");
		}
		
		if(null == endereco.getNumero()) {
			mensagem.append("O dado do numero do endereço de cobrança é obrigatorio");
		}
		
		if(null == endereco.getTipoLogradouro()) {
			mensagem.append("O dado do tipo de logradouro do cobrança de entrega é obrigatorio");
		}
		
		if(null == endereco.getTipoResidencia()){
			mensagem.append("O dado do tipo de residencia do cobrança de entrega é obrigatorio");
		}
		
		return mensagem.toString();
	}

}
