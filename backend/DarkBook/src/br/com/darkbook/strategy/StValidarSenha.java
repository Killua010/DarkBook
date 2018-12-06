package br.com.darkbook.strategy;

import br.com.darkbook.dominio.Cliente;
import br.com.darkbook.entidade.EntidadeDominio;

public class StValidarSenha extends AbstractStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		String senha = cliente.getUsuario().getSenha();
		
		boolean achouNumero = false;
	    boolean achouMaiuscula = false;
	    boolean achouMinuscula = false;
	    boolean achouSimbolo = false;
	    
	    for (char c : senha.toCharArray()) {
	         if (c >= '0' && c <= '9') {
	             achouNumero = true;
	         } else if (c >= 'A' && c <= 'Z') {
	             achouMaiuscula = true;
	         } else if (c >= 'a' && c <= 'z') {
	             achouMinuscula = true;
	         } else {
	             achouSimbolo = true;
	         }
	    }
		
		if(senha.length() < 8) {
			mensagem.append("A senha tem que ter mais de 8 caracteres");
		}
		
		if(!achouMinuscula) {
			mensagem.append("A senha tem que ter no minimo um caracter minusculo");
		}
		
		if(!achouMaiuscula) {
			mensagem.append("A senha tem que ter no minimo um caracter maiusculo");
		}
		
		if(!achouSimbolo) {
			mensagem.append("A senha tem que ter no minimo um caracter especial");
		}
			
		return mensagem.toString();
	}

}
