package br.com.darkbook.command;

import java.sql.SQLException;

import br.com.darkbook.cliente.dao.ClienteDAO;
import br.com.darkbook.entidade.Entidade;

public class SalvarCommand implements ICommand{

	@Override
	public void executar(Entidade entidade) throws SQLException {
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.adiciona(entidade);
		
		System.out.println("fim");
		
	}

}
