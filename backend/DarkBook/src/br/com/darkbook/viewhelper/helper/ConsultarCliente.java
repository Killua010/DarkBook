package br.com.darkbook.viewhelper.helper;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.darkbook.dominio.CartaoCredito;
import br.com.darkbook.dominio.Cliente;
import br.com.darkbook.dominio.Contato;
import br.com.darkbook.dominio.Endereco;
import br.com.darkbook.dominio.EnderecoEntrega;
import br.com.darkbook.dominio.Usuario;
import br.com.darkbook.entidade.Entidade;
import br.com.darkbook.util.Resultado;

public class ConsultarCliente implements IHelper {

	@Override
	public Entidade getEntidade(JSONObject objetoJson) {
		
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
		if(null == resultado.getEntidades()) {
			try {
				response.getWriter().write("Sem resultados");
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
    	for(Entidade ent : resultado.getEntidades()) {
    		Cliente cli = (Cliente) ent;
    		JSONObject clienteJson = new JSONObject();
    		JSONObject dadosPessoais = new JSONObject();
    		
    		dadosPessoais.put("id", cli.getId());
    		dadosPessoais.put("cpf", cli.getCpf());
    		dadosPessoais.put("nome", cli.getUsuario().getNome());
    		dadosPessoais.put("sobrenome", cli.getUsuario().getSobrenome());
    		dadosPessoais.put("dataNascimento", cli.getUsuario().getDataNascimento());
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
    			enderecoJson.put("observacao", endereco.getObservacao());
    			enderecoJson.put("nomeComposto", endereco.getNomeComposto());
    			enderecoJson.put("favorito", endereco.isFavorito());
    			enderecosEntrega.put(enderecoJson);
    		}
    		
    		clienteJson.put("enderecosEntrega", enderecosEntrega);
    		
    		JSONArray enderecosCobranca = new JSONArray();
    		for(Endereco endereco : cli.getEnderecos()) {
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
    			enderecoJson.put("observacao", endereco.getObservacao());
    			enderecosCobranca.put(enderecoJson);
    		}
    		
    		clienteJson.put("enderecosCobranca", enderecosCobranca);
    		
    		JSONArray cartoes = new JSONArray();
    		for(CartaoCredito cartao : cli.getCartoes()) {
    			JSONObject cartaoJson = new JSONObject();
    			cartaoJson.put("bandeira", cartao.getBandeira());
    			cartaoJson.put("numero", cartao.getNumero());
    			cartaoJson.put("nomeImpresso", cartao.getNomeImpresso());
    			cartaoJson.put("codSeguranca", cartao.getCodSeguranca());
    			cartaoJson.put("preferencial", cartao.isPreferencial());
    			cartoes.put(cartaoJson);
    		}
    		
    		clienteJson.put("cartoes", cartoes);
    		
    		response.setContentType("application/json");
    	    response.setCharacterEncoding("UTF-8");
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
