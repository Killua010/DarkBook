package br.com.darkbook.cliente.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.darkbook.JSONUtil;
import br.com.darkbook.cliente.Bandeira;
import br.com.darkbook.cliente.CartaoCredito;
import br.com.darkbook.cliente.Cliente;
import br.com.darkbook.cliente.dao.ClienteDAO;
import br.com.darkbook.contato.Contato;
import br.com.darkbook.contato.TipoTelefone;
import br.com.darkbook.endereco.Cidade;
import br.com.darkbook.endereco.Endereco;
import br.com.darkbook.endereco.EnderecoEntrega;
import br.com.darkbook.endereco.Estado;
import br.com.darkbook.endereco.Pais;
import br.com.darkbook.endereco.TipoLogradouro;
import br.com.darkbook.endereco.TipoResidencia;
import br.com.darkbook.entidade.Entidade;
import br.com.darkbook.fachada.Fachada;
import br.com.darkbook.fachada.IFachada;
import br.com.darkbook.usuario.Genero;
import br.com.darkbook.usuario.Usuario;

/**
 * @class ClienteController 
 * @author Daniel Dias de Souza 
 * @classdesc Controller de Cliente "/cliente"
 * 		novo             = POST "/"
 * 		pesquisar todos  = GET  "/"
 * 		pesquisar por id = GET  "/${id}"
 */
@WebServlet("/cliente")			// caminho da requisição
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IFachada fachada; 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		List<Entidade> clientesList = null;
		fachada = new Fachada();
		
		if(null != id) { // buscar por id
			
			Cliente cliente = new Cliente();
			cliente.setId(Long.parseLong(id));
			clientesList = fachada.consultar(cliente);
			
		} else { // buscar todos

			clientesList = fachada.consultar(new Cliente());
			
		}
		
		JSONObject clientes = new JSONObject();
    	for(Entidade ent : clientesList) {
    		Cliente cli = (Cliente) ent;
    		JSONObject dadosPessoais = new JSONObject();
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
    		
    		JSONArray enderecosEntrega = new JSONArray();
    		for(EnderecoEntrega endereco : cli.getEnderecoEntregas()) {
    			JSONObject enderecoJson = new JSONObject();
    			enderecoJson.put("tipoResidencia", endereco.getTipoResidencia());
    			enderecoJson.put("tipoLogradouro", endereco.getTipoLogradouro());
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
    		
    		dadosPessoais.put("enderecosEntrega", enderecosEntrega);
    		
    		JSONArray enderecosCobranca = new JSONArray();
    		for(Endereco endereco : cli.getEnderecos()) {
    			JSONObject enderecoJson = new JSONObject();
    			enderecoJson.put("tipoResidencia", endereco.getTipoResidencia());
    			enderecoJson.put("tipoLogradouro", endereco.getTipoLogradouro());
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
    		
    		dadosPessoais.put("enderecosCobranca", enderecosCobranca);
    		
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
    		
    		dadosPessoais.put("cartoes", cartoes);
    		
    		clientes.put("dadosPessoais", dadosPessoais);
    		
    		response.setContentType("application/json");
    	    response.setCharacterEncoding("UTF-8");
    	    
    	    response.getWriter().write(clientes.toString());
    	}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
			
		// instancias das classes
		Contato contato = new Contato();     		
 		Usuario usuario = new Usuario();
 		Cliente cliente = new Cliente();
 		Pais pais;
 		Estado estado;
 		Cidade cidade;
 		Endereco endereco;
 		EnderecoEntrega enderecoEntrega;
 		CartaoCredito cartaoCredito;
 		
    	try {
    		// Pegar a requisição JSON e transformar em um JSONObject
	     	JSONObject clienteJson = new JSONObject(JSONUtil.ParseString(request.getReader()));
     		
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
			endereco.setTipoLogradouro(TipoLogradouro.valueOf(enderecoJson.get("tipoLogradouro").toString()));
			endereco.setTipoResidencia(TipoResidencia.valueOf(enderecoJson.get("tipoResidencia").toString()));
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
			enderecoEntrega.setTipoLogradouro(TipoLogradouro.valueOf(enderecoEntregaJson.get("tipoLogradouro").toString()));
			enderecoEntrega.setTipoResidencia(TipoResidencia.valueOf(enderecoEntregaJson.get("tipoResidencia").toString()));
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
			cliente.setStatus(true);
			cliente.setDataHoraRegistro(LocalDateTime.now());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
    	
    	fachada = new Fachada();
    	
    	String erros = fachada.salvar(cliente); 
    	
    	if(!erros.isEmpty()) {
    		response.setStatus(400); // Erro 400 (Bad Request): parametros errados ou inexistentes
    		response.setHeader("Erros", erros);	// adiciona no header as mensagens de erros
    	}
		
	}
	
	

}
