package br.com.darkbook.strategy;

import java.util.List;

import br.com.darkbook.dao.ClienteDAO;
import br.com.darkbook.entidade.Entidade;
import br.com.darkbook.util.Resultado;

public class ValidarExistencia implements IStrategy{

	@Override
	public Resultado processar(Entidade entidade) {
		ClienteDAO cliDao;
		List<Entidade> clientes = null;
		
		cliDao = new ClienteDAO();
		clientes = cliDao.consultar(entidade);
		
			if(null == clientes) {
				return null;
			}
		
		
		return new Resultado("Cliente já Cadastrado", entidade);
		
	}

}
