package br.com.darkbook.cliente;

import java.util.InputMismatchException;
import java.util.List;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;

import br.com.darkbook.endereco.Endereco;
import br.com.darkbook.endereco.EnderecoEntrega;
import br.com.darkbook.entidade.Entidade;
import br.com.darkbook.usuario.Usuario;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

public class Cliente extends Entidade{
	private String cpf;
	private Usuario usuario;
	private List<CartaoCredito> cartoes;
	private List<EnderecoEntrega> enderecoEntregas;
	private List<Endereco> enderecos;
	
	/**
	 * @method validarDadosObrigatorios
	 * @description  metodo para validar se todos os dados obrigatórios de um cliente estão preenchidos
	 * @return String - mensagens dos erros dos dados não preenchidos
	 */
	public String validarDadosObrigatorios() {
		String erro = "";
		
		if(null == cpf || cpf.isEmpty()) {
			erro += "O dado de cpf é obrigatorio\n";
		}
		
		if(null == usuario) {
			
			erro += "O dado do ddd é obrigatorio\n";
			erro += "O dado do email é obrigatorio\n";
			erro += "O dado do numero telefonico é obrigatorio\n";
			erro += "O dado do tipo de telefone é obrigatorio\n";
			erro += "O dado da data de nascimento do cliente é obrigatorio\n";
			erro += "O dado de genero do cliente é obrigatorio\n";
			erro += "O dado do nome do do cliente é obrigatorio\n";
			erro += "O dado da senha do cliente é obrigatorio\n";
			erro += "O dado do sobrenome do cliente é obrigatorio\n";
			
		} else if(null == usuario.getContato()) {
			
			erro += "O dado do ddd é obrigatorio\n";
			erro += "O dado do email é obrigatorio\n";
			erro += "O dado do numero telefonico é obrigatorio\n";
			erro += "O dado do tipo de telefone é obrigatorio\n";
			
		} else {
			
			if(null == usuario.getContato().getDdd() || usuario.getContato().getDdd().isEmpty()) {
				erro += "O dado do ddd é obrigatorio\n";
			}
			
			if(null == usuario.getContato().getEmail() || usuario.getContato().getEmail().isEmpty()) {
				erro += "O dado do email é obrigatorio\n";
			}
			
			if(null == usuario.getContato().getNumero() || usuario.getContato().getNumero().isEmpty()) {
				erro += "O dado do numero telefonico é obrigatorio\n";
			}
			
			if(null == usuario.getContato().getTipoTelefone()) {
				erro += "O dado do tipo de telefone é obrigatorio\n";
			}
			
			if(null == usuario.getDataNascimento() || usuario.getDataNascimento().toString().isEmpty()) {
				erro += "O dado da data de nascimento do cliente é obrigatorio\n";
			}
			
			if(null == usuario.getGenero()) {
				erro += "O dado de genero do cliente é obrigatorio\n";
			}
			
			if(null == usuario.getNome() || usuario.getNome().isEmpty()) {
				erro += "O dado do nome do do cliente é obrigatorio\n";
			}
			
			if(null == usuario.getSenha() || usuario.getSenha().isEmpty()) {
				erro += "O dado da senha do cliente é obrigatorio\n";
			}
			
			if(null == usuario.getSobrenome() || usuario.getSobrenome().isEmpty()) {
				erro += "O dado do sobrenome do cliente é obrigatorio\n";
			}
			
		}
		
		if(null == cartoes) {
			
			erro += "O dado da bandeira do cartão é obrigatorio\n";
			erro += "O dado do código de segurança do cartão é obrigatorio\n";
			erro += "O dado do nome impresso do cartão é obrigatorio\n";
			erro += "O dado do numero do cartão é obrigatorio\n";
			
		} else {
			
			for(CartaoCredito car : cartoes) {
				erro = car.validarCartao();
			}
			
		}
		
		if(null == enderecoEntregas) {
			
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
			for(EnderecoEntrega endereco : enderecoEntregas) {
				erro = endereco.validarEndereco();
			}
			
		}
		
		if(null == enderecos) {
			
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
			
			for(Endereco endereco : enderecos) {
				erro = endereco.validarEndereco();
			}
			
		}
		
		return erro;
	}
	
	public String validarCPF() {

		if (cpf.equals("00000000000") ||
				cpf.equals("11111111111") ||
				cpf.equals("22222222222") || cpf.equals("33333333333") ||
				cpf.equals("44444444444") || cpf.equals("55555555555") ||
				cpf.equals("66666666666") || cpf.equals("77777777777") ||
				cpf.equals("88888888888") || cpf.equals("99999999999") ||
	            (cpf.length() != 11)) {
			return "O cpf informato é invalido";
		}
		
		
		char dig10, dig11;
        int sm, i, r, num, peso;
          
        // Calculo do 1o. Digito Verificador
        sm = 0;
        peso = 10;
        for (i=0; i<9; i++) {              
        // converte o i-esimo caractere do CPF em um numero:
        // por exemplo, transforma o caractere '0' no inteiro 0         
        // (48 eh a posicao de '0' na tabela ASCII)         
            num = (int)(cpf.charAt(i) - 48); 
            sm = sm + (num * peso);
            peso = peso - 1;
        }
          
        r = 11 - (sm % 11);
        
        if ((r == 10) || (r == 11)) {
                dig10 = '0';
        } else {
        	dig10 = (char)(r + 48); // converte no respectivo caractere numerico
        }
          
        // Calculo do 2o. Digito Verificador
        sm = 0;
        peso = 11;
        for(i=0; i<10; i++) {
            num = (int)(cpf.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
        }
          
        r = 11 - (sm % 11);
        
        if ((r == 10) || (r == 11)) {
             dig11 = '0';
        } else {
        	dig11 = (char)(r + 48);
        }
          
        // Verifica se os digitos calculados conferem com os digitos informados.
        if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))) {
             return "";
        } else { 
        	return "O cpf informato é invalido";
        }
	}
	
	
	public String validarSenha() {
		String senha = usuario.getSenha();
		
		if(senha.length() <= 6) {
			return "A senha tem que ter mais de 6 caracteres";
		} 
		return "";
	}
	
	
	
	/*
	 * Métodos GETs e SETs
	 */
	public List<EnderecoEntrega> getEnderecoEntregas() {
		return enderecoEntregas;
	}

	public void setEnderecoEntregas(List<EnderecoEntrega> enderecoEntregas) {
		this.enderecoEntregas = enderecoEntregas;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<CartaoCredito> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<CartaoCredito> cartoes) {
		this.cartoes = cartoes;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
