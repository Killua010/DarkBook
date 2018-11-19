package br.com.darkbook.viewhelper.helper;

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
import br.com.darkbook.entidade.Entidade;
import br.com.darkbook.util.JSONUtil;
import br.com.darkbook.util.Resultado;

public class SalvarCliente implements IHelper {
	
	@Override
	public void setEntidade(Resultado resultado, HttpServletResponse response) {
		try {
			if(resultado.getMensagens().length() != 0) {
	    		response.setStatus(400); // Erro 400 (Bad Request): parametros errados ou inexistentes
	    		response.setHeader("Erros", resultado.getMensagens().toString());	// adiciona no header as mensagens de erros
	    		response.getWriter().write(resultado.getMensagens().toString());
	    	} else {
	    		response.getWriter().write("Salvo com Sucesso");
	    	}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Entidade getEntidade(JSONObject objetoJson) {
		
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
	     	// caso os arrays não estejam instanciados, instancia-se
     		if (null == cliente.getCartoes())
     			cliente.setCartoes(new ArrayList<>());
     		
     		if (null == cliente.getEnderecoEntregas())
     			cliente.setEnderecoEntregas(new ArrayList<>());
     		
     		if (null == cliente.getEnderecos())
     			cliente.setEnderecos(new ArrayList<>());
     		
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
			
			JSONObject enderecoJson = clienteJson.getJSONObject("enderecosCobranca");
			pais = new Pais();
			estado = new Estado();
			cidade = new Cidade();
			endereco = new Endereco();
			pais.setPais(enderecoJson.get("pais").toString());
			estado.setEstado(enderecoJson.get("estado").toString());
			estado.setPais(pais);
			cidade.setCidade(enderecoJson.get("cidade").toString());
			cidade.setEstado(estado);
			
			endereco.setBairro(enderecoJson.get("bairro").toString());
			endereco.setCep(enderecoJson.get("cep").toString());
			endereco.setLogradouro(enderecoJson.get("logradouro").toString());
			endereco.setNumero(Integer.parseInt(enderecoJson.get("numero").toString()));
			endereco.setObservacao(enderecoJson.get("observacao").toString());
			
			tpr = new TipoResidencia();
      		tpr.setNome(enderecoJson.get("tipoResidencia").toString());
      		
      		tpl = new TipoLogradouro();
      		tpl.setNome(enderecoJson.get("tipoLogradouro").toString());
      		
      		endereco.setTipoLogradouro(tpl);
			endereco.setTipoResidencia(tpr);
			endereco.setCidade(cidade);
			
			JSONObject enderecoEntregaJson = clienteJson.getJSONObject("enderecosEntrega");
			pais = new Pais();
			estado = new Estado();
			cidade = new Cidade();
			enderecoEntrega = new EnderecoEntrega();
			pais.setPais(enderecoEntregaJson.get("pais").toString());
			estado.setEstado(enderecoEntregaJson.get("estado").toString());
			estado.setPais(pais);
			cidade.setCidade(enderecoEntregaJson.get("cidade").toString());
			cidade.setEstado(estado);
		
			enderecoEntrega.setBairro(enderecoEntregaJson.get("bairro").toString());
			enderecoEntrega.setCep(enderecoEntregaJson.get("cep").toString());
			enderecoEntrega.setLogradouro(enderecoEntregaJson.get("logradouro").toString());
			enderecoEntrega.setNumero(Integer.parseInt(enderecoEntregaJson.get("numero").toString()));
			enderecoEntrega.setObservacao(enderecoEntregaJson.get("observacao").toString());
			enderecoEntrega.setNomeComposto(enderecoEntregaJson.get("nomeComposto").toString());
			enderecoEntrega.setFavorito(Boolean.parseBoolean(enderecoEntregaJson.get("observacao").toString()));
			
			tpr = new TipoResidencia();
      		tpr.setNome(enderecoEntregaJson.get("tipoResidencia").toString());
      		
      		tpl = new TipoLogradouro();
      		tpl.setNome(enderecoEntregaJson.get("tipoLogradouro").toString());
			
			enderecoEntrega.setTipoLogradouro(tpl);
			enderecoEntrega.setTipoResidencia(tpr);
			enderecoEntrega.setCidade(cidade);
			
			JSONObject cartaoJson = clienteJson.getJSONObject("cartoes");
			cartaoCredito = new CartaoCredito();
			cartaoCredito.setBandeira(Bandeira.valueOf(cartaoJson.get("bandeira").toString()));
			cartaoCredito.setCodSeguranca(cartaoJson.get("codSeguranca").toString());
			cartaoCredito.setNomeImpresso(cartaoJson.get("nomeImpresso").toString());
			cartaoCredito.setNumero(cartaoJson.get("numero").toString());
			cartaoCredito.setPreferencial(Boolean.parseBoolean(cartaoJson.get("preferencial").toString()));
			
			cliente.setCpf(clienteJson.getJSONObject("dadosPessoais").get("cpf").toString());
			cliente.getCartoes().add(cartaoCredito);
			cliente.getEnderecos().add(endereco);
			cliente.setUsuario(usuario);
			
		}catch (Exception e) {}
    	
		return cliente;
	}

}
