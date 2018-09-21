package br.com.darkbook.cliente.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.darkbook.cliente.Cliente;
import br.com.darkbook.cliente.dao.ClienteDAO;
import br.com.darkbook.usuario.Genero;
import br.com.darkbook.usuario.Usuario;

/**
 * Servlet implementation class ClienteController
 */
@WebServlet("/cliente")
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClienteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				
		Map<String,String> json = new HashMap<String,String>();
		BufferedReader reader = request.getReader();
		String linha;
		
        while( (linha = reader.readLine()) != null ){
	        if(linha.trim().compareTo("{") != 0
	        		&& linha.trim().compareTo("}") != 0){
	        	linha = linha.replace(',', ' ');
	        	
	        	String[] jsons = linha.split(":");
	        	json.put(jsons[0].replaceAll("\"", "").trim().toString(), jsons[1].replaceAll("\"", "").trim().toString());
	        }
		}
        
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
        
        
		//doGet(request, response);
	}

}
