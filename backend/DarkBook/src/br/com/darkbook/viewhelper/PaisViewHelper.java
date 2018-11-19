package br.com.darkbook.viewhelper;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.darkbook.dominio.Cidade;
import br.com.darkbook.dominio.Estado;
import br.com.darkbook.dominio.Pais;
import br.com.darkbook.entidade.Entidade;
import br.com.darkbook.util.Resultado;

public class PaisViewHelper implements IViewHelper {

	@Override
	public Entidade getEntidade(HttpServletRequest request) {
		return new Pais();
	}

	@Override
	public void setEntidade(Resultado resultado, HttpServletResponse response) {
		JSONArray dadosEnderecoJson = new JSONArray();
		
		for(Entidade entidade :  resultado.getEntidades()) {
			Pais pais = (Pais) entidade;
			JSONObject paisJson = new JSONObject();
			
			paisJson.put("pais", pais.getPais());
			
			JSONArray estadoArray = new JSONArray();
			
			for(Estado estado : pais.getEstados()) {
				JSONObject estadoJson = new JSONObject();
				estadoJson.put("sigla", estado.getSigla());
				estadoJson.put("estado", estado.getEstado());
				
				JSONArray cidadeArray = new JSONArray();
				
				for(Cidade cidade : estado.getCidades()) {
					JSONObject cidadeJson = new JSONObject();
					cidadeJson.put("cidade", cidade.getCidade());
					cidadeArray.put(cidadeJson);
				}
				estadoJson.put("cidades", cidadeArray);
				estadoArray.put(estadoJson);
			}
			
			paisJson.put("estados", estadoArray);
			
			dadosEnderecoJson.put(paisJson);
			
		}
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		try {
			response.getWriter().write(dadosEnderecoJson.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
