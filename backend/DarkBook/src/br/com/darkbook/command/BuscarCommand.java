package br.com.darkbook.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.darkbook.cliente.dao.ClienteDAO;
import br.com.darkbook.entidade.Entidade;

public class BuscarCommand implements ICommand{

	@Override
	public void executar(Entidade entidade) throws SQLException {
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.buscar(entidade);
	}

}
