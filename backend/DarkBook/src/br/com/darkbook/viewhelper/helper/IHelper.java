package br.com.darkbook.viewhelper.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import br.com.darkbook.entidade.Entidade;
import br.com.darkbook.util.Resultado;

public interface IHelper {
	
	void setEntidade(Resultado resultado, HttpServletResponse response);

	Entidade getEntidade(JSONObject objetoJson);

}
