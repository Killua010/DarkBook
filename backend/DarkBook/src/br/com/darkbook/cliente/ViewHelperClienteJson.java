package br.com.darkbook.cliente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import br.com.darkbook.JSONUtil;
import br.com.darkbook.contato.Contato;
import br.com.darkbook.contato.Telefone;
import br.com.darkbook.contato.TipoTelefone;
import br.com.darkbook.endereco.Cidade;
import br.com.darkbook.endereco.Endereco;
import br.com.darkbook.endereco.EnderecoEntrega;
import br.com.darkbook.endereco.Estado;
import br.com.darkbook.endereco.Pais;
import br.com.darkbook.endereco.TipoLogradouro;
import br.com.darkbook.endereco.TipoResidencia;
import br.com.darkbook.entidade.Entidade;
import br.com.darkbook.usuario.Genero;
import br.com.darkbook.usuario.Usuario;

public class ViewHelperClienteJson {
	
    public Entidade getEntidade(HttpServletRequest request) {
    	
    	Telefone telefone = new Telefone();
     	Contato contato = new Contato();     		
 		Usuario usuario = new Usuario();
 		Pais pais = new Pais();
 		Estado estado = new Estado();
 		Cidade cidade = new Cidade();
 		Endereco endereco = new Endereco();
 		EnderecoEntrega enderecoEntrega = new EnderecoEntrega();
 		CartaoCredito cartaoCredito = new CartaoCredito();
 		Cliente cliente = new Cliente();
    	
    	try {
	     	JSONObject clienteJson = new JSONObject(JSONUtil.ParseString(request.getReader()));
	     	
	     	System.out.println(clienteJson);
     		if (null == contato.getTelefones())
     			contato.setTelefones(new ArrayList<>());
     		
     		if (null == usuario.getContatos())
     			usuario.setContatos(new ArrayList<>());
     		
     		if (null == cliente.getCartoes())
     			cliente.setCartoes(new ArrayList<>());
     		
     		if (null == cliente.getEnderecoEntregas())
     			cliente.setEnderecoEntregas(new ArrayList<>());
     		
     		if (null == cliente.getEnderecos())
     			cliente.setEnderecos(new ArrayList<>());
     		
     		
     		telefone.setTipoTelefone(TipoTelefone.valueOf(clienteJson.getJSONObject("dadosPessoais").get("tipoTelefone").toString()));
     		telefone.setNumero(clienteJson.getJSONObject("dadosPessoais").get("telefone").toString());
     		telefone.setDdd(clienteJson.getJSONObject("dadosPessoais").get("ddd").toString());
     		
     		contato.setEmail(clienteJson.getJSONObject("dadosPessoais").get("email").toString());
     		contato.getTelefones().add(telefone);
     		
			usuario.setNome(clienteJson.getJSONObject("dadosPessoais").get("nome").toString());
			usuario.setDataNascimento(LocalDate.parse(clienteJson.getJSONObject("dadosPessoais").get("dataNascimento").toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			usuario.setSobrenome(clienteJson.getJSONObject("dadosPessoais").get("sobrenome").toString());
			usuario.setGenero(Genero.valueOf(clienteJson.getJSONObject("dadosPessoais").get("genero").toString()));
			usuario.setSenha(clienteJson.getJSONObject("dadosPessoais").get("senha").toString());
			usuario.getContatos().add(contato);
			
			pais.setPais(clienteJson.getJSONObject("enderecoCobranca").get("pais").toString());
			estado.setEstado(clienteJson.getJSONObject("enderecoCobranca").get("estado").toString());
			estado.setPais(pais);
			cidade.setCidade(clienteJson.getJSONObject("enderecoCobranca").get("cidade").toString());
			cidade.setEstado(estado);
			
			endereco.setBairro(clienteJson.getJSONObject("enderecoCobranca").get("bairro").toString());
			endereco.setCep(clienteJson.getJSONObject("enderecoCobranca").get("cep").toString());
			endereco.setLogradouro(clienteJson.getJSONObject("enderecoCobranca").get("logradouro").toString());
			endereco.setNumero(Integer.parseInt(clienteJson.getJSONObject("enderecoCobranca").get("numero").toString()));
			endereco.setObservacao(clienteJson.getJSONObject("enderecoCobranca").get("observacao").toString());
			endereco.setTipoLogradouro(TipoLogradouro.valueOf(clienteJson.getJSONObject("enderecoCobranca").get("tipoLogradouro").toString()));
			endereco.setTipoResidencia(TipoResidencia.valueOf(clienteJson.getJSONObject("enderecoCobranca").get("tipoResidencia").toString()));
			endereco.setCidade(cidade);
			
			enderecoEntrega.setBairro(clienteJson.getJSONObject("enderecoEntrega").get("bairro").toString());
			enderecoEntrega.setCep(clienteJson.getJSONObject("enderecoEntrega").get("cep").toString());
			enderecoEntrega.setLogradouro(clienteJson.getJSONObject("enderecoEntrega").get("logradouro").toString());
			enderecoEntrega.setNumero(Integer.parseInt(clienteJson.getJSONObject("enderecoEntrega").get("numero").toString()));
			enderecoEntrega.setObservacao(clienteJson.getJSONObject("enderecoEntrega").get("observacao").toString());
			enderecoEntrega.setNomeComposto(clienteJson.getJSONObject("enderecoEntrega").get("nomeComposto").toString());
			enderecoEntrega.setFavorito(Boolean.parseBoolean(clienteJson.getJSONObject("enderecoEntrega").get("observacao").toString()));
			enderecoEntrega.setTipoLogradouro(TipoLogradouro.valueOf(clienteJson.getJSONObject("enderecoEntrega").get("tipoLogradouro").toString()));
			enderecoEntrega.setTipoResidencia(TipoResidencia.valueOf(clienteJson.getJSONObject("enderecoEntrega").get("tipoResidencia").toString()));
			enderecoEntrega.setCidade(cidade);
			
			cartaoCredito.setBandeira(Bandeira.valueOf(clienteJson.getJSONObject("cartao").get("bandeira").toString()));
			cartaoCredito.setCodSeguranca(clienteJson.getJSONObject("cartao").get("codSeguranca").toString());
			cartaoCredito.setNomeImpresso(clienteJson.getJSONObject("cartao").get("nomeImpresso").toString());
			cartaoCredito.setNumero(clienteJson.getJSONObject("cartao").get("numero").toString());
			cartaoCredito.setPreferencial(Boolean.parseBoolean(clienteJson.getJSONObject("cartao").get("preferencial").toString()));
			
			cliente.setCpf(clienteJson.getJSONObject("dadosPessoais").get("cpf").toString());
			cliente.getCartoes().add(cartaoCredito);
			cliente.getEnderecoEntregas().add(enderecoEntrega);
			cliente.getEnderecos().add(endereco);
			cliente.setUsuario(usuario);
			cliente.setStatus(true);
			cliente.setDataHoraRegistro(LocalDateTime.now());
			
			
		}catch (Exception e) {
			e.getMessage();
		}
    	
    	return cliente;
    	
    }

}
