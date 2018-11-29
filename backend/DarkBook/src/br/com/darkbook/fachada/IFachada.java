package br.com.darkbook.fachada;

import br.com.darkbook.entidade.EntidadeDominio;
import br.com.darkbook.util.Resultado;

public interface IFachada {
	
	public Resultado salvar(EntidadeDominio entidade);
	public Resultado consultar(EntidadeDominio entidade);
	public Resultado alterar(EntidadeDominio entidade);
	public Resultado excluir(EntidadeDominio entidade);

}
