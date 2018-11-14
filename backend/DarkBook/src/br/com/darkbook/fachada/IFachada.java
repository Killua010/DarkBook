package br.com.darkbook.fachada;

import java.util.List;

import br.com.darkbook.entidade.Entidade;
import br.com.darkbook.entidade.Entidade;

public interface IFachada {
	
	public String salvar(Entidade entidade);
	public List<Entidade> consultar(Entidade entidade);
	public void alterar(Entidade entidade);
	public void excluir(Entidade entidade);

}
