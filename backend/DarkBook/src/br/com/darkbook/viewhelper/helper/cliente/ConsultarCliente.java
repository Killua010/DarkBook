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

public class ConsultarCliente implements IHelper {

	@Override
	public EntidadeDominio getEntidade(JSONObject objetoJson) {
		
		Contato contato = new Contato();     		
 		Usuario usuario = new Usuario();
 		Cliente cliente = new Cliente();
		
		try {
			
	     	JSONObject clienteJson = objetoJson;
	 		
	 		try {
	 			cliente.setId(Long.parseLong(clienteJson.getJSONObject("dadosPessoais").get("id").toString()));
	 		}catch (Exception e) {}
	     	
	 		try {
	 			usuario.setSenha(clienteJson.getJSONObject("dadosPessoais").get("senha").toString());
	 			contato.setEmail(clienteJson.getJSONObject("dadosPessoais").get("email").toString());
	 		}catch (Exception e) {}
	 		
	     	usuario.setContato(contato);
	     	cliente.setUsuario(usuario);

		}catch (Exception e) {}
		
		return cliente;
	}

	@Override
	public void setEntidade(Resultado resultado, HttpServletResponse response) {
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    
	    
		if(null == resultado.getEntidades()) {
			try {
				response.setStatus(400); // Erro 400 (Bad Request): parametros errados ou inexistentes
	    		response.setHeader("Erros", "Sem resultados");	// adiciona no header as mensagens de erros
				response.getWriter().write("Sem resultados");
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(resultado.getEntidades().size() != 1) {
			try {
				response.setStatus(500); 
	    		response.setHeader("Erros", "Mais de um resultado");	// adiciona no header as mensagens de erros
				response.getWriter().write("Mais de um resultado");
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
    	for(EntidadeDominio ent : resultado.getEntidades()) {
    		Cliente cli = (Cliente) ent;
    		JSONObject clienteJson = new JSONObject();
    		JSONObject dadosPessoais = new JSONObject();
    		
    		dadosPessoais.put("id", cli.getId());
    		dadosPessoais.put("cpf", cli.getCpf());
    		dadosPessoais.put("nome", cli.getUsuario().getNome());
    		dadosPessoais.put("sobrenome", cli.getUsuario().getSobrenome());
    		dadosPessoais.put("dataNascimento", cli.getUsuario().getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    		dadosPessoais.put("genero", cli.getUsuario().getGenero());
    		dadosPessoais.put("senha", cli.getUsuario().getSenha());
    		dadosPessoais.put("email",  cli.getUsuario().getContato().getEmail());
    		dadosPessoais.put("ddd", cli.getUsuario().getContato().getDdd());
    		dadosPessoais.put("tipoTelefone", cli.getUsuario().getContato().getTipoTelefone());
    		dadosPessoais.put("telefone", cli.getUsuario().getContato().getNumero());
    		
    		clienteJson.put("dadosPessoais", dadosPessoais);
    		JSONArray enderecosEntrega = new JSONArray();
    		for(EnderecoEntrega endereco : cli.getEnderecoEntregas()) {
    			JSONObject enderecoJson = new JSONObject();
    			enderecoJson.put("tipoResidencia", endereco.getTipoResidencia().getNome());
    			enderecoJson.put("tipoLogradouro", endereco.getTipoLogradouro().getNome());
    			enderecoJson.put("pais", endereco.getCidade().getEstado().getPais().getPais());
    			enderecoJson.put("estado", endereco.getCidade().getEstado().getEstado());
    			enderecoJson.put("cidade", endereco.getCidade().getCidade());
    			enderecoJson.put("logradouro", endereco.getLogradouro());
    			enderecoJson.put("numero", endereco.getNumero());
    			enderecoJson.put("bairro", endereco.getBairro());
    			enderecoJson.put("cep", endereco.getCep());
    			enderecoJson.put("id", endereco.getId());
    			enderecoJson.put("observacao", endereco.getObservacao());
    			enderecoJson.put("nomeComposto", endereco.getNomeComposto());
    			enderecoJson.put("favorito", endereco.isFavorito());
    			enderecosEntrega.put(enderecoJson);
    		}
    		
    		clienteJson.put("enderecosEntrega", enderecosEntrega);

    		JSONArray enderecosCobranca = new JSONArray();
    		for(Endereco enderecoCobranca : cli.getEnderecoCobrancas()) {
    			JSONObject enderecoJson = new JSONObject();
    			enderecoJson.put("tipoResidencia", enderecoCobranca.getTipoResidencia().getNome());
    			enderecoJson.put("tipoLogradouro", enderecoCobranca.getTipoLogradouro().getNome());
    			enderecoJson.put("pais", enderecoCobranca.getCidade().getEstado().getPais().getPais());
    			enderecoJson.put("estado", enderecoCobranca.getCidade().getEstado().getEstado());
    			enderecoJson.put("cidade", enderecoCobranca.getCidade().getCidade());
    			enderecoJson.put("logradouro", enderecoCobranca.getLogradouro());
    			enderecoJson.put("numero", enderecoCobranca.getNumero());
    			enderecoJson.put("bairro", enderecoCobranca.getBairro());
    			enderecoJson.put("cep", enderecoCobranca.getCep());
    			enderecoJson.put("favorito", enderecoCobranca.isFavorito());
    			enderecoJson.put("observacao", enderecoCobranca.getObservacao());
    			enderecoJson.put("id", enderecoCobranca.getId());
    			enderecosCobranca.put(enderecoJson);
    		}
    		
    		clienteJson.put("enderecosCobranca", enderecosCobranca);
    		
    		JSONArray cartoes = new JSONArray();
    		for(CartaoCredito cartao : cli.getCartoes()) {
    			JSONObject cartaoJson = new JSONObject();
    			cartaoJson.put("bandeira", cartao.getBandeira().getNome());
    			cartaoJson.put("id", cartao.getId());
    			cartaoJson.put("numero", cartao.getNumero());
    			cartaoJson.put("nomeImpresso", cartao.getNomeImpresso());
    			cartaoJson.put("codSeguranca", cartao.getCodSeguranca());
    			cartaoJson.put("preferencial", cartao.isPreferencial());
    			cartoes.put(cartaoJson);
    		}
    		
    		clienteJson.put("cartoes", cartoes);
    		
    	    System.out.println(clienteJson.toString());
    	    try {
				response.getWriter().write(clienteJson.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}

	}

}
