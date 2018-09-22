package br.com.darkbook.cliente.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.darkbook.JSONUtil;
import br.com.darkbook.cliente.Cliente;
import br.com.darkbook.cliente.dao.ClienteDAO;
import br.com.darkbook.usuario.Genero;
import br.com.darkbook.usuario.Usuario;

@WebServlet("/cliente/*")
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ClienteController() {
        super();

    }

    private String[] tratarURL(String caminho, HttpServletResponse response) throws IOException {
    	String[] parametros = caminho.split("/");
    	
    	if(0 == parametros.length)
    		return parametros;
    	else if(parametros.length > 2 || !parametros[1].matches("^[0-9]{1,5}$")) {
    		response.setStatus(404);
    		return null;
    	}
    	
    	return parametros;	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String[] respostas = tratarURL(request.getPathInfo(), response);
		
		if(null == respostas || respostas.length > 0) {
			response.setStatus(404);
			return;
		}		
		
		// pega a requisição e converte o objeto JSON em um map de atributos
		Map<String,String> json = JSONUtil.ParseString(request.getReader());		
        
        Usuario usuario = new Usuario();
        usuario.setNome(json.get("nome"));
        usuario.setDataNascimento(LocalDate.parse(json.get("dataNascimento"), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        usuario.setSobrenome(json.get("sobrenome"));
        usuario.setGenero(Genero.valueOf(json.get("genero")));
        usuario.setSenha(json.get("senha"));
        
        Cliente cliente = new Cliente();
        cliente.setCpf(json.get("cpf"));
        cliente.setUsuario(usuario);
        
		ClienteDAO cliDao = new ClienteDAO();
		cliDao.adiciona(cliente);
        System.out.println("fim");
        
	}

}
