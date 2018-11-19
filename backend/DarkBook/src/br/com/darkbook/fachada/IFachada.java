package br.com.darkbook.fachada;

import br.com.darkbook.entidade.Entidade;
import br.com.darkbook.util.Resultado;

public interface IFachada {
	
	public Resultado salvar(Entidade entidade);
	public Resultado consultar(Entidade entidade);
	public Resultado alterar(Entidade entidade);
	public Resultado excluir(Entidade entidade);

}
