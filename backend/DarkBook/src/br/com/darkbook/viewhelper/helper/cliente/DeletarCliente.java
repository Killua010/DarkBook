package br.com.darkbook.viewhelper.helper.cliente;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.darkbook.dominio.CartaoCredito;
import br.com.darkbook.dominio.Cliente;
import br.com.darkbook.dominio.Contato;
import br.com.darkbook.dominio.Endereco;
import br.com.darkbook.dominio.EnderecoEntrega;
import br.com.darkbook.dominio.Usuario;
import br.com.darkbook.entidade.EntidadeDominio;
import br.com.darkbook.util.Resultado;
import br.com.darkbook.viewhelper.helper.IHelper;

public class DeletarCliente implements IHelper {

	@Override
	public EntidadeDominio getEntidade(JSONObject objetoJson) {
		
		Contato contato = new Contato();     		
 		Usuario usuario = new Usuario();
 		Cliente cliente = new Cliente();
		
		try {
			
	     	JSONObject clienteJson = objetoJson;
	 		
	 		
 			cliente.setId(Long.parseLong(clienteJson.getJSONObject("dadosPessoais").get("id").toString()));
	 		
	 		
	     	usuario.setContato(contato);
	     	cliente.setUsuario(usuario);

		}catch (Exception e) {}
		
		return cliente;
	}

	@Override
	public void setEntidade(Resultado resultado, HttpServletResponse response) {
		try {
			if(resultado.getMensagens().length() != 0) {
	    		response.setStatus(400); // Erro 400 (Bad Request): parametros errados ou inexistentes
	    		response.setHeader("Erros", resultado.getMensagens().toString());	// adiciona no header as mensagens de erros
	    		response.getWriter().write(resultado.getMensagens().toString());
	    	} else {
	    		response.getWriter().write("Inativado com Sucesso");
	    	}
		}catch (Exception e) {
			e.printStackTrace();
    	}

	}

}
