package br.com.darkbook.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.darkbook.JSONUtil;
import br.com.darkbook.cliente.Cliente;
import br.com.darkbook.cliente.dao.ClienteDAO;
import br.com.darkbook.usuario.Genero;
import br.com.darkbook.usuario.Usuario;

public class SalvarCommand implements ICommand{

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// pega a requisição e converte o objeto JSON em um map de atributos
		Map<String,String> json = JSONUtil.ParseString(request.getReader());		
        try {
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
			//cliDao.adiciona(cliente);
        } catch(Exception e) {
        	
        }
        System.out.println("fim");
	}

}
