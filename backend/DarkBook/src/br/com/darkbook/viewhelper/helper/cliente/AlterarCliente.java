package br.com.darkbook.viewhelper.helper.cliente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import br.com.darkbook.dominio.Bandeira;
import br.com.darkbook.dominio.CartaoCredito;
import br.com.darkbook.dominio.Cidade;
import br.com.darkbook.dominio.Cliente;
import br.com.darkbook.dominio.Contato;
import br.com.darkbook.dominio.Endereco;
import br.com.darkbook.dominio.EnderecoEntrega;
import br.com.darkbook.dominio.Estado;
import br.com.darkbook.dominio.Genero;
import br.com.darkbook.dominio.Pais;
import br.com.darkbook.dominio.TipoLogradouro;
import br.com.darkbook.dominio.TipoResidencia;
import br.com.darkbook.dominio.TipoTelefone;
import br.com.darkbook.dominio.Usuario;
import br.com.darkbook.entidade.EntidadeDominio;
import br.com.darkbook.util.Resultado;
import br.com.darkbook.viewhelper.helper.IHelper;

public class AlterarCliente implements IHelper {
	
	@Override
	public void setEntidade(Resultado resultado, HttpServletResponse response) {
		try {
			if(resultado.getMensagens().length() != 0) {
	    		response.setStatus(400); // Erro 400 (Bad Request): parametros errados ou inexistentes
	    		response.setHeader("Erros", resultado.getMensagens().toString());	// adiciona no header as mensagens de erros
	    		response.getWriter().write(resultado.getMensagens().toString());
	    	} else {
	    		response.getWriter().write("Alterado com Sucesso");
	    	}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public EntidadeDominio getEntidade(JSONObject objetoJson) {
		
		JSONObject clienteJson = objetoJson;
     	System.out.println(clienteJson);
		
		Contato contato = new Contato();     		
 		Usuario usuario = new Usuario();
 		Cliente cliente = new Cliente();
 		Pais pais;
 		Estado estado;
 		Cidade cidade;
 		Endereco endereco;
 		EnderecoEntrega enderecoEntrega;
 		CartaoCredito cartaoCredito;
 		TipoResidencia tpr;
  		TipoLogradouro tpl;
 		
    	try {
     		
     		// preencher os objetos com os dados da requisição 
     		contato.setTipoTelefone(TipoTelefone.valueOf(clienteJson.getJSONObject("dadosPessoais").get("tipoTelefone").toString()));
     		contato.setNumero(clienteJson.getJSONObject("dadosPessoais").get("telefone").toString());
     		contato.setDdd(clienteJson.getJSONObject("dadosPessoais").get("ddd").toString());
     		contato.setEmail(clienteJson.getJSONObject("dadosPessoais").get("email").toString());
     		
			usuario.setNome(clienteJson.getJSONObject("dadosPessoais").get("primeiroNome").toString());
			usuario.setDataNascimento(LocalDate.parse(clienteJson.getJSONObject("dadosPessoais").get("dataNascimento").toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			usuario.setSobrenome(clienteJson.getJSONObject("dadosPessoais").get("sobrenome").toString());
			usuario.setGenero(Genero.valueOf(clienteJson.getJSONObject("dadosPessoais").get("genero").toString()));
			usuario.setSenha(clienteJson.getJSONObject("dadosPessoais").get("senha").toString());
			usuario.setContato(contato);
			
			cliente.setUsuario(usuario);
			cliente.setCpf(clienteJson.getJSONObject("dadosPessoais").get("cpf").toString());
			cliente.setId(Long.parseLong(clienteJson.getJSONObject("dadosPessoais").get("id").toString()));
			
		}catch (Exception e) {}
	    	System.out.println(clienteJson.getJSONObject("dadosPessoais").get("primeiroNome").toString());
	    	System.out.println(cliente.getUsuario().getNome());
		return cliente;
	}

}
