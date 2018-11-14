package br.com.darkbook.fachada;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.darkbook.dao.ClienteDAO;
import br.com.darkbook.dao.IDAO;
import br.com.darkbook.dao.PaisDAO;
import br.com.darkbook.dao.TipoLogradouroDAO;
import br.com.darkbook.dao.TipoResidenciaDAO;
import br.com.darkbook.dominio.Cliente;
import br.com.darkbook.dominio.Pais;
import br.com.darkbook.dominio.TipoLogradouro;
import br.com.darkbook.dominio.TipoResidencia;
import br.com.darkbook.entidade.Entidade;
import br.com.darkbook.strategy.ComplementarDataCadastro;
import br.com.darkbook.strategy.IStrategy;
import br.com.darkbook.strategy.ValidarCPF;
import br.com.darkbook.strategy.ValidarDadosObrigatorios;
import br.com.darkbook.strategy.ValidarExistencia;
import br.com.darkbook.strategy.ValidarSenha;
import br.com.darkbook.util.Resultado;

public class Fachada implements IFachada {
	
	private Map<String, List<IStrategy>> mapStrategy;
	private Map<String, IDAO> mapDao;
	// construtor
	public Fachada() throws ClassNotFoundException, SQLException {
		mapStrategy = new HashMap<>();
		mapDao = new HashMap<>();
		
		ArrayList<IStrategy> clienteStrategy = new ArrayList<>();
		clienteStrategy.add(new ValidarDadosObrigatorios());
		clienteStrategy.add(new ValidarCPF());
		clienteStrategy.add(new ValidarExistencia());
		clienteStrategy.add(new ValidarSenha());
		clienteStrategy.add(new ComplementarDataCadastro());
		
		mapStrategy.put(Cliente.class.getName(), clienteStrategy);
		
		mapDao.put(Cliente.class.getName(), new ClienteDAO());
		mapDao.put(Pais.class.getName(), new PaisDAO());
		mapDao.put(TipoLogradouro.class.getName(), new TipoLogradouroDAO());
		mapDao.put(TipoResidencia.class.getName(), new TipoResidenciaDAO());
	}

	@Override
	public String salvar(Entidade entidade) {
		
		String erros = "";
		
		for(IStrategy strategy : mapStrategy.get(entidade.getClass().getName())) {
			Resultado r = strategy.processar(entidade);
			if(r != null) {
				erros += r.getMensagem();
			}
				
		}
    	if(erros.isEmpty()) {
    		IDAO dao = mapDao.get(entidade.getClass().getName());
    		dao.salvar(entidade);
    	}
    	
    	System.out.println(erros);
    	
    	return erros;

	}

	@Override
	public List<Entidade> consultar(Entidade entidade) {
		
		IDAO dao = mapDao.get(entidade.getClass().getName());
		
		return dao.consultar(entidade);
		
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