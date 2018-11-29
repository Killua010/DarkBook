package br.com.darkbook.viewhelper.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import br.com.darkbook.entidade.EntidadeDominio;
import br.com.darkbook.util.Resultado;

public interface IHelper {
	
	void setEntidade(Resultado resultado, HttpServletResponse response);

	EntidadeDominio getEntidade(JSONObject objetoJson);

}
