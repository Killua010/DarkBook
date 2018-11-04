package br.com.darkbook.fachada;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.darkbook.cliente.Cliente;
import br.com.darkbook.cliente.dao.ClienteDAO;
import br.com.darkbook.dao.IDAO;
import br.com.darkbook.endereco.Pais;
import br.com.darkbook.endereco.dao.PaisDAO;
import br.com.darkbook.entidade.Entidade;
import br.com.darkbook.strategy.IStrategy;
import br.com.darkbook.strategy.ValidarCPF;
import br.com.darkbook.strategy.ValidarDadosObrigatorios;
import br.com.darkbook.strategy.ValidarExistencia;
import br.com.darkbook.strategy.ValidarSenha;

public class Fachada implements IFachada {
	
	private Map<String, List<IStrategy>> mapStrategy;
	private Map<String, IDAO> mapDao;
	
	public Fachada() throws ClassNotFoundException, SQLException {
		mapStrategy = new HashMap<>();
		mapDao = new HashMap<>();
		
		ArrayList<IStrategy> clienteStrategy = new ArrayList<>();
		clienteStrategy.add(new ValidarDadosObrigatorios());
		clienteStrategy.add(new ValidarCPF());
		clienteStrategy.add(new ValidarExistencia());
		clienteStrategy.add(new ValidarSenha());
		
		mapStrategy.put(Cliente.class.getName(), clienteStrategy);
		
		mapDao.put(Cliente.class.getName(), new ClienteDAO());
		mapDao.put(Pais.class.getName(), new PaisDAO());
	}

	@Override
	public String salvar(Entidade entidade) {
		
		String erros = "";
		
		for(IStrategy strategy : mapStrategy.get(entidade.getClass().getName())) {
			erros += strategy.processar(entidade);
		}
    	
    	if(erros.isEmpty()) {
    		IDAO dao = mapDao.get(entidade.getClass().getName());
    		dao.salvar(entidade);
    	}
    	
    	return erros;

	}

	@Override
	public List<Entidade> consultar(Entidade entidade) {
		List<Entidade> entidades = null;
		IDAO dao = mapDao.get(entidade.getClass().getName());
		
		entidades = dao.consultar(entidade);
		
		return entidades;
		
	}

	@Override
	public void alterar(Entidade entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(Entidade entidade) {
		// TODO Auto-generated method stub

	}
	


}
