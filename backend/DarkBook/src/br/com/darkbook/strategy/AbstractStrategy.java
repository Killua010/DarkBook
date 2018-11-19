package br.com.darkbook.strategy;

public abstract class AbstractStrategy implements IStrategy {

	protected StringBuilder mensagem = new StringBuilder();
	
	protected String verificaMsg() {
		if(mensagem.length() > 0) {
			return mensagem.toString();
		}else {
			return null;
		}
	}
}
