package br.com.darkbook.strategy;

import java.util.List;

import br.com.darkbook.dao.ClienteDAO;
import br.com.darkbook.entidade.Entidade;

public class ValidarExistencia implements IStrategy{

	@Override
	public String processar(Entidade entidade) {
		ClienteDAO cliDao;
		List<Entidade> clientes = null;
		
		cliDao = new ClienteDAO();
		clientes = cliDao.consultar(entidade);
		
		if(null == clientes) {
			return null;
		}			
		
		return "Cliente já Cadastrado";
		
	}

}
