package br.com.darkbook.viewhelper;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.darkbook.dominio.Cidade;
import br.com.darkbook.dominio.Estado;
import br.com.darkbook.dominio.Pais;
import br.com.darkbook.dominio.TipoResidencia;
import br.com.darkbook.entidade.EntidadeDominio;
import br.com.darkbook.util.Resultado;

public class TipoResidenciaViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		return new TipoResidencia();
	}

	@Override
	public void setEntidade(Resultado resultado, HttpServletResponse response) {
		JSONArray tipoResidenciaJson = new JSONArray();
		
		for(EntidadeDominio entidade :  resultado.getEntidades()) {
			tipoResidenciaJson.put(((TipoResidencia)entidade).getNome());
		}
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		try {
			response.getWriter().write(tipoResidenciaJson.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
