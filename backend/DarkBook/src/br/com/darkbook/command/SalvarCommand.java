package br.com.darkbook.command;

import br.com.darkbook.entidade.Entidade;

public class SalvarCommand implements ICommand{

	@Override
	public void executar(Entidade entidade) {
		System.out.println("fim");
		
	}

}
