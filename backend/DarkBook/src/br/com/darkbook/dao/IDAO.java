package br.com.darkbook.dao;

import java.util.List;

import br.com.darkbook.entidade.Entidade;

public interface IDAO {
	
	public void salvar(Entidade entidade);
	public List<Entidade> consultar(Entidade entidade);
	public void alterar(Entidade entidade);
	public void excluir(Entidade entidade);

}
