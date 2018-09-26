package br.com.darkbook.cliente.controller;

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

import br.com.darkbook.JSONUtil;
import br.com.darkbook.cliente.Cliente;
import br.com.darkbook.cliente.ViewHelperClienteJson;
import br.com.darkbook.cliente.dao.ClienteDAO;
import br.com.darkbook.command.BuscarCommand;
import br.com.darkbook.command.BuscarPorIdCommand;
import br.com.darkbook.command.ICommand;
import br.com.darkbook.command.SalvarCommand;
import br.com.darkbook.usuario.Genero;
import br.com.darkbook.usuario.Usuario;

@WebServlet("/cliente/*")
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Map<String, ICommand> comandosPost;
	private Map<String, ICommand> comandosGet;
       
    public ClienteController() {
        super();
        comandosPost = new HashMap<String, ICommand>();
        comandosPost.put("/", new SalvarCommand());
        
        comandosGet = new HashMap<String, ICommand>();
        comandosGet.put("/", new BuscarCommand());
        comandosGet.put("/id", new BuscarPorIdCommand());
    }

    private String tratarURL(String caminho, HttpServletResponse response) throws IOException {
    	if(null == caminho)
    		return "/";
    	
    	String[] parametros = caminho.split("/");
    	
    	if(0 == parametros.length)
    		return "/";
    	else if(parametros.length > 2 || !parametros[1].matches("^[0-9]{1,5}$")) {
    		return null;
    	}
    	
    	return "/id";	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ViewHelperClienteJson vhCli = new ViewHelperClienteJson();
			ICommand comando = comandosPost.get(tratarURL(request.getPathInfo(), response));
			comando.executar(vhCli.getEntidade(request));
		}catch (Exception e) {
			response.setStatus(404);
		}       
	}

}
