package br.com.darkbook.fachada;

import java.sql.SQLException;
import java.util.List;

import br.com.darkbook.cliente.Cliente;
import br.com.darkbook.cliente.dao.ClienteDAO;
import br.com.darkbook.entidade.Entidade;

public class Fachada implements IFachada {

	@Override
	public String salvar(Entidade entidade) {
		Cliente cliente = (Cliente) entidade;
		
		String erros = "";
		
		erros += cliente.validarDadosObrigatorios(); 
    	
    	
    	erros += cliente.validarCPF();
    	
    	
    	if(!validarExistencia(cliente)){
    		erros += "Cliente já existente";
    	}
    	
    	erros += cliente.validarSenha();
    	
    	if(erros.isEmpty()) {
	    	try {
				ClienteDAO cliDao = new ClienteDAO();
				cliDao.salvar(cliente);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
    	}
    	
    	return erros;

	}

	@Override
	public List<Entidade> consultar(Entidade entidade) {
		List<Entidade> clientesList = null;
		try {
			ClienteDAO cliDao = new ClienteDAO();
			clientesList = cliDao.consultar(entidade);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clientesList;
		
	}

	@Override
	public void alterar(Entidade entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(Entidade entidade) {
		// TODO Auto-generated method stub

	}
	
	public boolean validarExistencia(Cliente cli) {
		ClienteDAO cliDao;
		List<Entidade> clientes = null;
		
		try {
			cliDao = new ClienteDAO();
			clientes = cliDao.consultar(cli);
			
		} catch (ClassNotFoundException | SQLException e1) {

			e1.printStackTrace();
		}
		for(Entidade cliente : clientes) {
			if(null == cliente.getId()) {
				return true;
			}
		}
		
		return false;
	}

}
