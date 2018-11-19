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
	private Resultado resultado;
	// construtor
	public Fachada() {
		mapStrategy = new HashMap<String, List<IStrategy>>();
		mapDao = new HashMap<String, IDAO>();
		
		ArrayList<IStrategy> clienteStrategy = new ArrayList<>();
		clienteStrategy.add(new ValidarDadosObrigatorios());
		clienteStrategy.add(new ValidarCPF());
		clienteStrategy.add(new ValidarExistencia());
		clienteStrategy.add(new ValidarSenha());
		clienteStrategy.add(new ComplementarDataCadastro());
		
		mapStrategy.put(Cliente.class.getName(), clienteStrategy);
		
		mapDao.put(Cliente.class.getName(), new ClienteDAO());
		try {
			mapDao.put(Pais.class.getName(), new PaisDAO());
			mapDao.put(TipoLogradouro.class.getName(), new TipoLogradouroDAO());
			mapDao.put(TipoResidencia.class.getName(), new TipoResidenciaDAO());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Resultado salvar(Entidade entidade) {
		resultado = new Resultado();
		
		executarStrategys(entidade, mapStrategy.get(entidade.getClass().getName()));
		
    	if(resultado.getMensagens().length() == 0) {
    		IDAO dao = mapDao.get(entidade.getClass().getName());
    		dao.salvar(entidade);
    	}
    	resultado.addEntidade(entidade);
    	return resultado;

	}

	@Override
	public Resultado consultar(Entidade entidade) {
		
		IDAO dao = mapDao.get(entidade.getClass().getName());
		resultado = new Resultado();
		resultado.setEntidades(dao.consultar(entidade));
		
		return resultado;
		
	}

	@Override
	public Resultado alterar(Entidade entidade) {
		return null;
		// TODO Auto-generated method stub

	}

	@Override
	public Resultado excluir(Entidade entidade) {
		return null;
		// TODO Auto-generated method stub

	}
	
	private List<IStrategy> executarStrategys(Entidade entidade, List<IStrategy> strategys) {
		for(IStrategy str : strategys) {
			String mensagem = str.processar(entidade);
			if(mensagem != null) {
				resultado.getMensagens().append(mensagem);
			}
		}
		return strategys;
	}
	


}
