package br.com.darkbook.strategy;

import br.com.darkbook.dominio.CartaoCredito;
import br.com.darkbook.dominio.Cliente;
import br.com.darkbook.dominio.Endereco;
import br.com.darkbook.dominio.EnderecoEntrega;
import br.com.darkbook.entidade.EntidadeDominio;

public class StValidarDadosObrigatorios extends AbstractStrategy {

	/**
	 * @method validarDadosObrigatorios
	 * @description  metodo para validar se todos os dados obrigatórios de um cliente estão preenchidos
	 * @return String - mensagemns dos erros dos dados não preenchidos
	 */
	@Override
	public String processar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		IStrategy strategy;
		
		if(null == cliente.getCpf() || cliente.getCpf().isEmpty()) {
			mensagem.append("O dado de cpf é obrigatorio\n");
		}
		
		if(null == cliente.getUsuario()) {
			
			mensagem.append("O dado do ddd é obrigatorio\n");
			mensagem.append("O dado do email é obrigatorio\n");
			mensagem.append("O dado do numero telefonico é obrigatorio\n");
			mensagem.append("O dado do tipo de telefone é obrigatorio\n");
			mensagem.append("O dado da data de nascimento do cliente é obrigatorio\n");
			mensagem.append("O dado de genero do cliente é obrigatorio\n");
			mensagem.append("O dado do nome do do cliente é obrigatorio\n");
			mensagem.append("O dado da senha do cliente é obrigatorio\n");
			mensagem.append("O dado do sobrenome do cliente é obrigatorio\n");
			
		} else if(null == cliente.getUsuario().getContato()) {
			
			mensagem.append("O dado do ddd é obrigatorio\n");
			mensagem.append("O dado do email é obrigatorio\n");
			mensagem.append("O dado do numero telefonico é obrigatorio\n");
			mensagem.append("O dado do tipo de telefone é obrigatorio\n");
			
		} else {
			
			if(null == cliente.getUsuario().getContato().getDdd() || cliente.getUsuario().getContato().getDdd().isEmpty()) {
				mensagem.append("O dado do ddd é obrigatorio\n");
			}
			
			if(null == cliente.getUsuario().getContato().getEmail() || cliente.getUsuario().getContato().getEmail().isEmpty()) {
				mensagem.append("O dado do email é obrigatorio\n");
			}
			
			if(null == cliente.getUsuario().getContato().getNumero() || cliente.getUsuario().getContato().getNumero().isEmpty()) {
				mensagem.append("O dado do numero telefonico é obrigatorio\n");
			}
			
			if(null == cliente.getUsuario().getContato().getTipoTelefone()) {
				mensagem.append("O dado do tipo de telefone é obrigatorio\n");
			}
			
			if(null == cliente.getUsuario().getDataNascimento() || cliente.getUsuario().getDataNascimento().toString().isEmpty()) {
				mensagem.append("O dado da data de nascimento do cliente é obrigatorio\n");
			}
			
			if(null == cliente.getUsuario().getGenero()) {
				mensagem.append("O dado de genero do cliente é obrigatorio\n");
			}
			
			if(null == cliente.getUsuario().getNome() || cliente.getUsuario().getNome().isEmpty()) {
				mensagem.append("O dado do nome do do cliente é obrigatorio\n");
			}
			
			if(null == cliente.getUsuario().getSenha() || cliente.getUsuario().getSenha().isEmpty()) {
				mensagem.append("O dado da senha do cliente é obrigatorio\n");
			}
			
			if(null == cliente.getUsuario().getSobrenome() || cliente.getUsuario().getSobrenome().isEmpty()) {
				mensagem.append("O dado do sobrenome do cliente é obrigatorio\n");
			}
			
		}
		
		if(null == cliente.getCartoes()) {
			
			mensagem.append("O dado da bandeira do cartão é obrigatorio\n");
			mensagem.append("O dado do código de segurança do cartão é obrigatorio\n");
			mensagem.append("O dado do nome impresso do cartão é obrigatorio\n");
			mensagem.append("O dado do numero do cartão é obrigatorio\n");
			
		} else {
			
			strategy = new StValidarCartao();
			
			for(CartaoCredito car : cliente.getCartoes()) {
				mensagem.append(strategy.processar(car));
			}
			
		}
		
		if(null == cliente.getEnderecoEntregas()) {
			
			mensagem.append("O dado do bairro do endereço de entrega é obrigatorio\n");
			mensagem.append("O dado do cep do endereço de entrega é obrigatorio\n");
			mensagem.append("O dado da cidade do endereço de entrega é obrigatorio\n");
			mensagem.append("O dado do estado do endereço de entrega é obrigatorio\n");
			mensagem.append("O dado do pais do endereço de entrega é obrigatorio\n");
			mensagem.append("O dado do logradouro do endereço de entrega é obrigatorio\n");
			mensagem.append("O dado do nome composto do endereço de entrega é obrigatorio\n");
			mensagem.append("O dado do numero do endereço de entrega é obrigatorio\n");
			mensagem.append("O dado do tipo de logradouro do endereço de entrega é obrigatorio\n");
			mensagem.append("O dado do tipo de residencia do endereço de entrega é obrigatorio\n");
			
		} else {
			
			strategy = new StValidarEnderecoEntrega();
			
			for(EnderecoEntrega endereco : cliente.getEnderecoEntregas()) {
				mensagem.append(strategy.processar(endereco));
			}
			
		}
		
		if(null == cliente.getEnderecoCobrancas()) {
			
			mensagem.append("O dado do bairro do endereço de cobrança é obrigatorio\n");
			mensagem.append("O dado do cep do endereço de cobrança é obrigatorio\n");
			mensagem.append("O dado da cidade do endereço de cobrança é obrigatorio\n");
			mensagem.append("O dado do estado do endereço de cobrança é obrigatorio\n");
			mensagem.append("O dado do pais do endereço de cobrança é obrigatorio\n");
			mensagem.append("O dado do logradouro do endereço de cobrança é obrigatorio\n");
			mensagem.append("O dado do numero do endereço de cobrança é obrigatorio\n");
			mensagem.append("O dado do tipo de logradouro do cobrança de entrega é obrigatorio\n");
			mensagem.append("O dado do tipo de residencia do cobrança de entrega é obrigatorio\n");
			
		} else {
			
			strategy = new StValidarEndereco();
			
			for(Endereco enderecoCobranca : cliente.getEnderecoCobrancas()) {
				mensagem.append(strategy.processar(enderecoCobranca));
			}
			
		}
		
		return mensagem.toString();
	}

}
