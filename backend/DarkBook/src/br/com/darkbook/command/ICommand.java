package br.com.darkbook.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import br.com.darkbook.entidade.Entidade;

public interface ICommand {

	public void executar(Entidade entidade);
}
