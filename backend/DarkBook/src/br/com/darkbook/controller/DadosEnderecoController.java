package br.com.darkbook.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.darkbook.dominio.Cidade;
import br.com.darkbook.dominio.Estado;
import br.com.darkbook.dominio.Pais;
import br.com.darkbook.dominio.TipoLogradouro;
import br.com.darkbook.dominio.TipoResidencia;
import br.com.darkbook.entidade.Entidade;
import br.com.darkbook.entidade.Entidade;
import br.com.darkbook.fachada.Fachada;
import br.com.darkbook.fachada.IFachada;

@WebServlet("/dadosEndereco")
public class DadosEnderecoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private IFachada fachada;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Entidade> paisList = null;
		List<Entidade> tiposLogradouroList = null;
		List<Entidade> tiposResidenciaList = null;
		
		try {
			fachada = new Fachada();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		paisList = fachada.consultar(new Pais());
		
		JSONObject dadosEnderecoJson = new JSONObject();
		
		for(Entidade entidade :  paisList) {
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
			
			dadosEnderecoJson.put("paises", paisJson);
			
		}
		
		tiposLogradouroList = fachada.consultar(new TipoLogradouro());
		JSONArray tiposLogradouroListJson = new JSONArray();
		
		for(Entidade entidade :  tiposLogradouroList) {
			TipoLogradouro tipoLogradouro = (TipoLogradouro) entidade;
			JSONObject tipoLogradouroJson = new JSONObject();
			tipoLogradouroJson.put("nome", tipoLogradouro.getNome());
			tipoLogradouroJson.put("id", tipoLogradouro.getId());
			tiposLogradouroListJson.put(tipoLogradouroJson);
		}
		
		tiposResidenciaList = fachada.consultar(new TipoResidencia());
		JSONArray tiposResidenciaListJson = new JSONArray();
		
		for(Entidade entidade :  tiposResidenciaList) {
			TipoResidencia tipoResidencia = (TipoResidencia) entidade;
			JSONObject tipoResidenciaJson = new JSONObject();
			tipoResidenciaJson.put("nome", tipoResidencia.getNome());
			tipoResidenciaJson.put("id", tipoResidencia.getId());
			tiposResidenciaListJson.put(tipoResidenciaJson);
		}
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		response.getWriter().write(dadosEnderecoJson.toString());
		
		
	}

}
