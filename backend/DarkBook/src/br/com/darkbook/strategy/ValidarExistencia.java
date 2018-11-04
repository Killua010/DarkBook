package br.com.darkbook.strategy;

import java.sql.SQLException;
import java.util.List;

import br.com.darkbook.cliente.dao.ClienteDAO;
import br.com.darkbook.entidade.Entidade;

public class ValidarExistencia implements IStrategy{

	@Override
	public String processar(Entidade entidade) {
		ClienteDAO cliDao;
		List<Entidade> clientes = null;
		
		try {
			cliDao = new ClienteDAO();
			clientes = cliDao.consultar(entidade);
			
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
		for(Entidade cliente : clientes) {
			if(null == cliente) {
				return "";
			}
		}
		
		return "Cliente já Cadastrado";
		
	}

}
