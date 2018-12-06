package br.com.darkbook.dao;

import java.util.List;

import br.com.darkbook.entidade.EntidadeDominio;

public interface IDAO {
	
	public void salvar(EntidadeDominio entidade);
	public List<EntidadeDominio> consultar(EntidadeDominio entidade);
	public void alterar(EntidadeDominio entidade);
	public void excluir(EntidadeDominio entidade);

}
