package br.com.darkbook.viewhelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.darkbook.entidade.EntidadeDominio;
import br.com.darkbook.util.Resultado;

public interface IViewHelper {
	
	EntidadeDominio getEntidade(HttpServletRequest request);
	
	void setEntidade(Resultado resultado, HttpServletResponse response);

}
