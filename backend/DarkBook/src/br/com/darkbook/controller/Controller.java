package br.com.darkbook.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.darkbook.command.AlterarCommand;
import br.com.darkbook.command.ConsultarCommand;
import br.com.darkbook.command.ExcluirCommand;
import br.com.darkbook.command.ICommand;
import br.com.darkbook.command.SalvarCommand;
import br.com.darkbook.dominio.TipoLogradouro;
import br.com.darkbook.dominio.TipoResidencia;
import br.com.darkbook.entidade.EntidadeDominio;
import br.com.darkbook.viewhelper.BandeiraCartaoViewHelper;
import br.com.darkbook.viewhelper.ClienteViewHelper;
import br.com.darkbook.viewhelper.IViewHelper;
import br.com.darkbook.viewhelper.PaisViewHelper;
import br.com.darkbook.viewhelper.TipoLogradouroViewHelper;
import br.com.darkbook.viewhelper.TipoResidenciaViewHelper;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Map<String, ICommand> commands;
	private Map<String, IViewHelper> viewHelpers;
	private ICommand command;
	private EntidadeDominio entidade;
    
	public Controller() {
		super();
		
		this.commands = new HashMap<String, ICommand>();
		commands.put("SALVAR", new SalvarCommand());
		commands.put("ALTERAR", new AlterarCommand());
		commands.put("EXCLUIR", new ExcluirCommand());
		commands.put("CONSULTAR", new ConsultarCommand());
		
		this.viewHelpers = new HashMap<String, IViewHelper>();
		viewHelpers.put("/DarkBook/cliente", new ClienteViewHelper());
		viewHelpers.put("/DarkBook/paises", new PaisViewHelper());
		viewHelpers.put("/DarkBook/tipoLogradouro", new TipoLogradouroViewHelper());
		viewHelpers.put("/DarkBook/tipoResidencia", new TipoResidenciaViewHelper());
		viewHelpers.put("/DarkBook/bandeira", new BandeiraCartaoViewHelper());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		IViewHelper vh = viewHelpers.get(request.getRequestURI());
		
		entidade = vh.getEntidade(request);
		
		command = commands.get(request.getParameter("operacao"));
		
		vh.setEntidade(command.executar(entidade), response);
		
	}
	
	

}
