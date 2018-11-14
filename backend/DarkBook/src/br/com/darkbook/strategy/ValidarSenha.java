package br.com.darkbook.strategy;

import br.com.darkbook.dominio.Cliente;
import br.com.darkbook.entidade.Entidade;
import br.com.darkbook.util.Resultado;

public class ValidarSenha implements IStrategy{

	@Override
	public Resultado processar(Entidade entidade) {
		Cliente cliente = (Cliente) entidade;
		
		String senha = cliente.getUsuario().getSenha();
		
		if(senha.length() <= 6) {
			return new Resultado("A senha tem que ter mais de 6 caracteres", entidade);
		} 
		return null;
	}

}
