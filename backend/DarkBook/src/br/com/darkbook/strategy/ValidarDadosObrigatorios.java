package br.com.darkbook.strategy;

import br.com.darkbook.cliente.CartaoCredito;
import br.com.darkbook.cliente.Cliente;
import br.com.darkbook.endereco.Endereco;
import br.com.darkbook.endereco.EnderecoEntrega;
import br.com.darkbook.entidade.Entidade;

public class ValidarDadosObrigatorios implements IStrategy {

	/**
	 * @method validarDadosObrigatorios
	 * @description  metodo para validar se todos os dados obrigatórios de um cliente estão preenchidos
	 * @return String - mensagens dos erros dos dados não preenchidos
	 */
	@Override
	public String processar(Entidade entidade) {
		Cliente cliente = (Cliente) entidade;
		
		String erro = "";
		
		if(null == cliente.getCpf() || cliente.getCpf().isEmpty()) {
			erro += "O dado de cpf é obrigatorio\n";
		}
		
		if(null == cliente.getUsuario()) {
			
			erro += "O dado do ddd é obrigatorio\n";
			erro += "O dado do email é obrigatorio\n";
			erro += "O dado do numero telefonico é obrigatorio\n";
			erro += "O dado do tipo de telefone é obrigatorio\n";
			erro += "O dado da data de nascimento do cliente é obrigatorio\n";
			erro += "O dado de genero do cliente é obrigatorio\n";
			erro += "O dado do nome do do cliente é obrigatorio\n";
			erro += "O dado da senha do cliente é obrigatorio\n";
			erro += "O dado do sobrenome do cliente é obrigatorio\n";
			
		} else if(null == cliente.getUsuario().getContato()) {
			
			erro += "O dado do ddd é obrigatorio\n";
			erro += "O dado do email é obrigatorio\n";
			erro += "O dado do numero telefonico é obrigatorio\n";
			erro += "O dado do tipo de telefone é obrigatorio\n";
			
		} else {
			
			if(null == cliente.getUsuario().getContato().getDdd() || cliente.getUsuario().getContato().getDdd().isEmpty()) {
				erro += "O dado do ddd é obrigatorio\n";
			}
			
			if(null == cliente.getUsuario().getContato().getEmail() || cliente.getUsuario().getContato().getEmail().isEmpty()) {
				erro += "O dado do email é obrigatorio\n";
			}
			
			if(null == cliente.getUsuario().getContato().getNumero() || cliente.getUsuario().getContato().getNumero().isEmpty()) {
				erro += "O dado do numero telefonico é obrigatorio\n";
			}
			
			if(null == cliente.getUsuario().getContato().getTipoTelefone()) {
				erro += "O dado do tipo de telefone é obrigatorio\n";
			}
			
			if(null == cliente.getUsuario().getDataNascimento() || cliente.getUsuario().getDataNascimento().toString().isEmpty()) {
				erro += "O dado da data de nascimento do cliente é obrigatorio\n";
			}
			
			if(null == cliente.getUsuario().getGenero()) {
				erro += "O dado de genero do cliente é obrigatorio\n";
			}
			
			if(null == cliente.getUsuario().getNome() || cliente.getUsuario().getNome().isEmpty()) {
				erro += "O dado do nome do do cliente é obrigatorio\n";
			}
			
			if(null == cliente.getUsuario().getSenha() || cliente.getUsuario().getSenha().isEmpty()) {
				erro += "O dado da senha do cliente é obrigatorio\n";
			}
			
			if(null == cliente.getUsuario().getSobrenome() || cliente.getUsuario().getSobrenome().isEmpty()) {
				erro += "O dado do sobrenome do cliente é obrigatorio\n";
			}
			
		}
		
		if(null == cliente.getCartoes()) {
			
			erro += "O dado da bandeira do cartão é obrigatorio\n";
			erro += "O dado do código de segurança do cartão é obrigatorio\n";
			erro += "O dado do nome impresso do cartão é obrigatorio\n";
			erro += "O dado do numero do cartão é obrigatorio\n";
			
		} else {
			
			for(CartaoCredito car : cliente.getCartoes()) {
				erro = car.validarCartao();
			}
			
		}
		
		if(null == cliente.getEnderecoEntregas()) {
			
			erro += "O dado do bairro do endereço de entrega é obrigatorio\n";
			erro += "O dado do cep do endereço de entrega é obrigatorio\n";
			erro += "O dado da cidade do endereço de entrega é obrigatorio\n";
			erro += "O dado do estado do endereço de entrega é obrigatorio\n";
			erro += "O dado do pais do endereço de entrega é obrigatorio\n";
			erro += "O dado do logradouro do endereço de entrega é obrigatorio\n";
			erro += "O dado do nome composto do endereço de entrega é obrigatorio\n";
			erro += "O dado do numero do endereço de entrega é obrigatorio\n";
			erro += "O dado do tipo de logradouro do endereço de entrega é obrigatorio\n";
			erro += "O dado do tipo de residencia do endereço de entrega é obrigatorio\n";
			
		} else {
			for(EnderecoEntrega endereco : cliente.getEnderecoEntregas()) {
				erro = endereco.validarEndereco();
			}
			
		}
		
		if(null == cliente.getEnderecos()) {
			
			erro += "O dado do bairro do endereço de cobrança é obrigatorio\n";
			erro += "O dado do cep do endereço de cobrança é obrigatorio\n";
			erro += "O dado da cidade do endereço de cobrança é obrigatorio\n";
			erro += "O dado do estado do endereço de cobrança é obrigatorio\n";
			erro += "O dado do pais do endereço de cobrança é obrigatorio\n";
			erro += "O dado do logradouro do endereço de cobrança é obrigatorio\n";
			erro += "O dado do numero do endereço de cobrança é obrigatorio\n";
			erro += "O dado do tipo de logradouro do cobrança de entrega é obrigatorio\n";
			erro += "O dado do tipo de residencia do cobrança de entrega é obrigatorio\n";
			
		} else {
			
			for(Endereco endereco : cliente.getEnderecos()) {
				erro = endereco.validarEndereco();
			}
			
		}
		
		return erro;
	}

}
