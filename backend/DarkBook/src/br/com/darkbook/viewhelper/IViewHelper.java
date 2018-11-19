package br.com.darkbook.viewhelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.darkbook.entidade.Entidade;
import br.com.darkbook.util.Resultado;

public interface IViewHelper {
	
	Entidade getEntidade(HttpServletRequest request);
	
	void setEntidade(Resultado resultado, HttpServletResponse response);

}
