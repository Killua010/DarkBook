package br.com.darkbook.strategy;

import br.com.darkbook.dominio.Cliente;
import br.com.darkbook.entidade.Entidade;
import br.com.darkbook.util.Resultado;

public class ValidarCPF implements IStrategy {

	@Override
	public Resultado processar(Entidade entidade) {
		Cliente cliente = (Cliente) entidade;
		
		if (cliente.getCpf().equals("00000000000") ||
				cliente.getCpf().equals("11111111111") ||
				cliente.getCpf().equals("22222222222") || cliente.getCpf().equals("33333333333") ||
				cliente.getCpf().equals("44444444444") || cliente.getCpf().equals("55555555555") ||
				cliente.getCpf().equals("66666666666") || cliente.getCpf().equals("77777777777") ||
				cliente.getCpf().equals("88888888888") || cliente.getCpf().equals("99999999999") ||
	            (cliente.getCpf().length() != 11)) {
			return new Resultado("O cpf informato é invalido", entidade);
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
            num = (int)(cliente.getCpf().charAt(i) - 48); 
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
            num = (int)(cliente.getCpf().charAt(i) - 48);
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
        if ((dig10 == cliente.getCpf().charAt(9)) && (dig11 == cliente.getCpf().charAt(10))) {
             return null;
        } else { 
        	return new Resultado("O cpf informato é invalido", entidade);
        }
	}

}
